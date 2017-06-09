package com.mycompany.esb.filesprocessor;


import org.apache.camel.RuntimeCamelException;
import org.apache.camel.builder.RouteBuilder;





public class RouteBuilderBean extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		onException(RuntimeCamelException.class).continued(true);
		
		from("file:D://src/data?recursive=true&delete=true&idempotent=true&idempotentRepository=#jpaStore")
				.routeId("job_route")
				.log("message in file :${file:name} and ${file:ext}")				
				.log("valid files will be processed to destination")
				.to("file:D://src/output");
				
				

	}

}
