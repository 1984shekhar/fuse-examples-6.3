
package com.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
 
import javax.management.MBeanServer;
import javax.management.ObjectName;
 
import org.apache.camel.ContextTestSupport;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
 
/**
 * @version 
 */
public class LanguageLoadScriptFromFileCachedTest extends ContextTestSupport {
 
    
    @Override
    protected void setUp() throws Exception {
        deleteDirectory("target/script");
        super.setUp();
    }
     
    @Override
    public boolean useJmx() {
        return true;
    }
 
    public void testLanguage() throws Exception {
        getMockEndpoint("mock:result").expectedBodiesReceived("Hello World", "Hello World");
 
        template.sendBody("direct:start", "World");
 
        // even if we update the file the content is cached
        template.sendBodyAndHeader("file:target/script", "Bye ${body}", Exchange.FILE_NAME, "myscript.txt");
        template.sendBody("direct:start", "World");
 
        assertMockEndpointsSatisfied();
    }
     
    public void testClearCachedScriptViaJmx() throws Exception {
        getMockEndpoint("mock:result").expectedBodiesReceived("Hello World", "Hello World", "Bye World");
 
        template.sendBody("direct:start", "World");
 
        // even if we update the file the content is cached
        Map<String, Object> headers = new HashMap<String, Object>();
        headers.put(Exchange.FILE_NAME, "myscript.txt");
        headers.put("pathname", "target/script/myscript.txt");
        template.sendBodyAndHeaders("file:target/script", "println \"Bye ${body}\"; result = \"Bye ${body}\"", headers);
        template.sendBody("direct:start", "World");
 
        // now clear the cache via the mbean server
        MBeanServer mbeanServer = context.getManagementStrategy().getManagementAgent().getMBeanServer();
        Set<ObjectName> objNameSet = mbeanServer.queryNames(
            //new ObjectName("org.apache.camel:type=endpoints,name=\"language://groovy:*contentCache=true*\",*"), null);
            //    new ObjectName("org.apache.camel:type=endpoints,name=\"language://groovy:*\",*"), null);
                new ObjectName("org.apache.camel:type=endpoints,name=\"file:*\",*"), null);
        System.out.println("all beans: " + mbeanServer.queryNames(null,null));
        System.out.println("objectnameset: " + objNameSet.toString());
        ObjectName managedObjName = new ArrayList<ObjectName>(objNameSet).get(0);
        mbeanServer.invoke(managedObjName, "getCamelId", null, null);
 
        template.sendBody("direct:start", "World");
 
        assertMockEndpointsSatisfied();
    }
 
    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                // create script to start with
                template.sendBodyAndHeader("file:target/script", "println \"Hello ${body}\"; result = \"Hello ${body}\"", Exchange.FILE_NAME, "myscript.txt");
 
                // START SNIPPET: e1
                from("direct:start")
                    // use content cache to load the script once and cache it (content cache and script cache both enabled)
                    //.to("language:groovy:file:target/script/myscript.txt?contentCache=true&cacheScript=true")
                //.to("language:groovy:file:target/script/myscript.txt")
                .setHeader("pathname", simple("target/script/myscript.txt"))
                .log("body ${body}")
                .log("filename: ${headers.pathname}")
                
                
               /* .recipientList(simple("language:groovy:file:${headers.pathname}"))*/
              //  .to(endpoint)
                //.to("language:groovy:file:target/script/myscript.txt")
                   .to("language:groovy:file:{{headers.pathname}}");
                // END SNIPPET: e1
            }
        };
    }
}