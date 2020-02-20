package com.mycompany.activemq.poc.replyTo;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

public class RequestProcessor implements Processor {

	Logger logger = Logger.getLogger(this.getClass());
	private static int counter = 1;
	private int sleepTimer = 40000;
	@Override
	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub
		
		logger.info("counter: "+ counter );
        if (counter%3 == 0){
        	logger.info( "And sleeping for " + sleepTimer + "ms");
        	Thread.sleep(sleepTimer);
        }
         counter++;
	}

}
