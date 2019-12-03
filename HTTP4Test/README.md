  osgi:install -s mvn:com.mycompany/camel-http4-test/1.0



OQL:

HTTP4:

SELECT hc, hc.connectionTimeToLive, hc.connectionsPerRoute FROM INSTANCEOF org.apache.camel.component.http4.HttpComponent hc 

select * from org.apache.camel.component.jetty9.JettyHttpComponent9


SELECT j, j.camelContext.nameStrategy.name.toString() FROM org.apache.camel.component.jetty9.JettyHttpComponent9 j


JETTY9:

SELECT j, j._port, j._executor._minThreads, j._executor._maxThreads FROM org.eclipse.jetty.server.ServerConnector j 



SELECT j, j.camelContext.nameStrategy.name.toString(), j.minThreads.value, j.maxThreads.value FROM org.apache.camel.component.jetty9.JettyHttpComponent9 j 
