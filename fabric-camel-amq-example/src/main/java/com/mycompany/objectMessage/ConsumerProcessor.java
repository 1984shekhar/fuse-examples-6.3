package com.mycompany.objectMessage;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

public class ConsumerProcessor implements Processor{
	private static int counter = 0;
	private Logger logger = Logger.getLogger(this.getClass());

	@Override
	public void process(Exchange arg0) throws Exception {
		
		
		Person person = new Person();
		person.setAge(33);
		person.setName("CSP_"+counter);
		logger.info("person: "+person.getName());
		arg0.getIn().setBody(person, Person.class);
		counter++;
	}

}
