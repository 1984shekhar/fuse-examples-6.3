package com.mycompany.test.profile;


import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogTransaction {
	

	private Logger LOG= LoggerFactory.getLogger(LogTransaction.class);
	public void setExchange(Exchange exchange) {
		
		Person bi = exchange.getIn().getBody(Person.class);
		LOG.info("Object Bi= "+String.valueOf(bi));
		LOG.info("String Bi= "+bi.getName());
	}
}