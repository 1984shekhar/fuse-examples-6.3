1. This is an example where we can avoid logs from beling polluted by exception stack-trace while redelivery.

    osgi:install -s mvn:com.mycompany/camel-blueprint/1.0.0-SNAPSHOT

2. With logStackTrace and logRetryStackTrace set as false, it will not print below stack trace(Step 3) of DefaultErrorHandler.

	<bean id="redeliveryPolicy" class="org.apache.camel.processor.RedeliveryPolicy">
		---
		---
		<property name="logStackTrace" value="false" />
		<property name="logRetryStackTrace" value="false" />
	</bean>




3. 2019-11-01 17:26:06,005 | ERROR | onsumer[request] | DefaultErrorHandler              | rg.apache.camel.util.CamelLogger  204 | 198 - org.apache.camel.camel-core - 2.17.0.redhat-630396 | Failed delivery for (MessageId: ID-cpandey-pnq-csb-32919-1572604759520-15-3 on ExchangeId: ID-cpandey-pnq-csb-32919-1572604759520-15-2). Exhausted after delivery attempt: 4 caught: java.lang.RuntimeException: ...TEST ERROR.... Processed by failure processor: FatalFallbackErrorHandler[Channel[sendTo(Endpoint[activemq://queue:transactionTest1])]]n  | n  | Message Historyn  | ---------------------------------------------------------------------------------------------------------------------------------------n  | RouteId              ProcessorId          Processor                                                                        Elapsed (ms)n  | [route2            ] [route2            ] [activemq://request                                                            ] [     15024]n  | [route2            ] [_convertBodyTo2   ] [convertBodyTo[java.lang.String]                                               ] [         1]n  | [route2            ] [_log2             ] [log                                                                           ] [         0]n  | [route2            ] [process11         ] [ref:processorbean                                                             ] [     15023]n  | [                  ] [_to1              ] [activemq:queue:transactionTest1                                               ] [        21]n  | n  | Stacktracen  | ---------------------------------------------------------------------------------------------------------------------------------------
java.lang.RuntimeException: ...TEST ERROR...
	at com.mycompany.test.ErrorProcessor.process(ErrorProcessor.java:14)[231:errorHandlerLogStackTrace:1.0.0.SNAPSHOT]
	at org.apache.camel.processor.DelegateSyncProcessor.process(DelegateSyncProcessor.java:63)[198:org.apache.camel.camel-core:2.17.0.redhat-630396]
	at org.apache.camel.management.InstrumentationProcessor.process(InstrumentationProcessor.java:77)[198:org.apache.camel.camel-core:2.17.0.redhat-630396]
	at org.apache.camel.processor.RedeliveryErrorHandler.process(RedeliveryErrorHandler.java:468)[198:org.apache.camel.camel-core:2.17.0.redhat-630396]
	at org.apache.camel.processor.CamelInternalProcessor.process(CamelInternalProcessor.java:196)[198:org.apache.camel.camel-core:2.17.0.redhat-630396]
	at org.apache.camel.processor.Pipeline.process(Pipeline.java:121)[198:org.apache.camel.camel-core:2.17.0.redhat-630396]
	at org.apache.camel.processor.Pipeline.process(Pipeline.java:83)[198:org.apache.camel.camel-core:2.17.0.redhat-630396]
	at org.apache.camel.processor.CamelInternalProcessor.process(CamelInternalProcessor.java:196)[198:org.apache.camel.camel-core:2.17.0.redhat-630396]
	at org.apache.camel.util.AsyncProcessorHelper.process(AsyncProcessorHelper.java:109)[198:org.apache.camel.camel-core:2.17.0.redhat-630396]
	at org.apache.camel.processor.DelegateAsyncProcessor.process(DelegateAsyncProcessor.java:91)[198:org.apache.camel.camel-core:2.17.0.redhat-630396]
	at org.apache.camel.component.jms.EndpointMessageListener.onMessage(EndpointMessageListener.java:112)[223:org.apache.camel.camel-jms:2.17.0.redhat-630396]
	at org.springframework.jms.listener.AbstractMessageListenerContainer.doInvokeListener(AbstractMessageListenerContainer.java:555)[222:org.apache.servicemix.bundles.spring-jms:3.2.18.RELEASE_1]
	at org.springframework.jms.listener.AbstractMessageListenerContainer.invokeListener(AbstractMessageListenerContainer.java:515)[222:org.apache.servicemix.bundles.spring-jms:3.2.18.RELEASE_1]
	at org.springframework.jms.listener.AbstractMessageListenerContainer.doExecuteListener(AbstractMessageListenerContainer.java:485)[222:org.apache.servicemix.bundles.spring-jms:3.2.18.RELEASE_1]
	at org.springframework.jms.listener.AbstractPollingMessageListenerContainer.doReceiveAndExecute(AbstractPollingMessageListenerContainer.java:325)[222:org.apache.servicemix.bundles.spring-jms:3.2.18.RELEASE_1]
	at org.springframework.jms.listener.AbstractPollingMessageListenerContainer.receiveAndExecute(AbstractPollingMessageListenerContainer.java:263)[222:org.apache.servicemix.bundles.spring-jms:3.2.18.RELEASE_1]
	at org.springframework.jms.listener.DefaultMessageListenerContainer$AsyncMessageListenerInvoker.invokeListener(DefaultMessageListenerContainer.java:1103)[222:org.apache.servicemix.bundles.spring-jms:3.2.18.RELEASE_1]
	at org.springframework.jms.listener.DefaultMessageListenerContainer$AsyncMessageListenerInvoker.executeOngoingLoop(DefaultMessageListenerContainer.java:1095)[222:org.apache.servicemix.bundles.spring-jms:3.2.18.RELEASE_1]
	at org.springframework.jms.listener.DefaultMessageListenerContainer$AsyncMessageListenerInvoker.run(DefaultMessageListenerContainer.java:992)[222:org.apache.servicemix.bundles.spring-jms:3.2.18.RELEASE_1]
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1142)[:1.8.0_121]
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:617)[:1.8.0_121]
