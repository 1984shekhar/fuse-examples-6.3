package com.mycompany.objectMessage;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

public class ProducerProcessor implements Processor {
	
	private Logger logger = Logger.getLogger(this.getClass());

	@Override
	public void process(Exchange arg0) throws Exception {
		
		Person person = arg0.getIn().getBody(Person.class);
		logger.info("Name: "+ person.getName()+" Age: "+person.getAge());
		
	}

}
