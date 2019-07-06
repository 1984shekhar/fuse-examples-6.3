package com.test.csp;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

public class MyProcessor implements Processor {
	Logger logger = Logger.getLogger(this.getClass());
	private int count;
	@Override
	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub
		if (++count <= 20) {
			logger.info("count = "+count);
            throw new IllegalArgumentException("Forced Exception number " + count + ", please retry");
        }
        exchange.getIn().setBody("Bye World");
        exchange.getIn().setHeader("count", count);

	}

}
