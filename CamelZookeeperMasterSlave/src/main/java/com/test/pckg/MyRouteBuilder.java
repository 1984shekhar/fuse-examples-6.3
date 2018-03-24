package com.test.pckg;
import java.util.Date;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.zookeeper.policy.ZooKeeperRoutePolicy;

/**
 * A Camel Java DSL Router
 */
public class MyRouteBuilder extends RouteBuilder {

    /**
     * Let's configure the Camel routing rules using Java code...
     */
	@Override
    public void configure() {

        System.out.println("......Inside MyRouteBuilder.....");
        ZooKeeperRoutePolicy policy = new ZooKeeperRoutePolicy("zookeeper:localhost:" + 2181 + "/someapp/somepolicy", 1);
        from("file:/home/cpandey/masterSlaveRoute").routePolicy(policy).id("Master").log("Body... ${body} "+new Date());
    }
}