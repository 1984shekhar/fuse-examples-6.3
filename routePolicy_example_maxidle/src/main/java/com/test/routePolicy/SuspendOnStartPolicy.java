package com.test.routePolicy;


import org.apache.camel.*;
import org.apache.camel.support.RoutePolicySupport;
import org.apache.camel.util.StopWatch;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Route Policy for manual resume and automatic suspend of the route.
 * Will wait for X seconds (defined by the maxIdleSeconds property) before suspending the route if no incoming messages.
 */
public class SuspendOnStartPolicy extends RoutePolicySupport implements CamelContextAware {

    /** Needed to create the SingleThreadScheduledExecutor */
    private CamelContext camelContext;
    /** Result of the scheduled task, needed for cancel when necessary */
    private static ScheduledFuture<?> future;
    /** Simple stopwatch to elapsed time between consumed messages */
    private StopWatch watch;
    /** Defines the max idle period before suspeding the route */
    private long maxIdleSeconds;


    public SuspendOnStartPolicy(long maxIdleSeconds) {
        super();
        this.maxIdleSeconds = maxIdleSeconds;
    }

    @Override
    public void onStart(Route route) {
        scheduleSuspend(route);
    }

    @Override
    public void onResume(Route route) {
        log.trace("Route " + route.getId() + " manually resumed.");
        scheduleSuspend(route);
    }

    @Override
    public void onExchangeDone(Route route, Exchange exchange) {
        watch.restart();
    }

    @Override
    public void setCamelContext(CamelContext camelContext) {
        this.camelContext = camelContext;
    }

    @Override
    public CamelContext getCamelContext() {
        return camelContext;
    }

    /**
     * Suspend route if no message were consumed for a set period of time.
     * See https://github.com/apache/camel/blob/master/camel-core/src/main/java/org/apache/camel/main/MainDurationEventNotifier.java#L116
     * @param route The route to suspend.
     */
    private void scheduleSuspend(Route route) {
        ScheduledExecutorService executorService = camelContext.getExecutorServiceManager().newSingleThreadScheduledExecutor(this, "MainDurationIdleChecker");
        watch = new StopWatch();

        Runnable task = () -> {
            // Check if there are some in-flight messages
            int inFlight = camelContext.getInflightRepository().size();
            if (inFlight > 0) {
                log.info("Duration max idle check is skipped due to {} in-flight messages", inFlight);
                return;
            }

            long seconds = watch.taken() / 1000;
            boolean itIsTimeToStop = seconds >= maxIdleSeconds;
            log.trace("Duration max idle check {} >= {} -> {}", seconds, maxIdleSeconds, itIsTimeToStop);

            if (itIsTimeToStop) {
                log.info("Duration max idle triggering suspend of the route.");
                try {
                    suspendRoute(route);
                } catch (Exception e) {
                    log.error("Cannot suspend route " + route.getId(), e);
                } finally {
                    // Terminate task
                    future.cancel(false);
                }
            }
        };
        future = executorService.scheduleAtFixedRate(task, 1, 1, TimeUnit.SECONDS);
    }
}
