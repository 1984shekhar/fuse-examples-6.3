package com.test.csp;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class MyProcessor implements Processor {
	private int count;
	@Override
	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub
		if (++count <= 7) {
            throw new IllegalArgumentException("Forced Exception number " + count + ", please retry");
        }
        exchange.getIn().setBody("Bye World");
        exchange.getIn().setHeader("count", count);

	}

}
