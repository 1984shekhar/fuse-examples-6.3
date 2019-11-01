package com.mycompany.test;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

public class ErrorProcessor implements Processor {

	Logger logger = Logger.getLogger(this.getClass());
	@Override
	public void process(Exchange exchange) throws Exception {
		
		logger.info("Within ErrorProcessor");
		throw new RuntimeException("...TEST ERROR...");
	}

}
