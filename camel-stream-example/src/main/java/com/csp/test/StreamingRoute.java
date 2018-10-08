package com.csp.test;

import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class StreamingRoute extends RouteBuilder
{
	private static final Logger LOG = LoggerFactory.getLogger(StreamingRoute.class);
	
	@Override
	public void configure() throws Exception
	{	
		
	/*	from("jetty:http://localhost:9090/testUrl")
		  .pollEnrich("stream:file?fileName=/home/cpandey/NotBackedUp/Development/RedHat_Fuse_Folder/jboss-fuse-6.3.0.redhat-347/data/log/fuse1.log&scanStream=true&scanStreamDelay=1000&groupLines=1")
		  .log("Fin streaming ${in.body}");
		*/
		from("stream:file?fileName=/home/cpandey/NotBackedUp/Development/RedHat_Fuse_Folder/jboss-fuse-6.3.0.redhat-347/data/log/fuse1.log&scanStream=true&scanStreamDelay=1000").log("Fin streaming ${in.body}");
	}
}
