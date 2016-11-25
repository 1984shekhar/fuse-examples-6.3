package com.mycompany.verify.crypto;



import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.processor.aggregate.AggregationStrategy;

public class verifyExchange extends RouteBuilder {

    /**
     * Let's configure the Camel routing rules using Java code...
     */
	@Override
    public void configure() {

        System.out.println("......Inside MyRouteBuilder.....");

from("file:/home/cpandey/Desktop/TestingCrypto/signed?antInclude=*.signature&move=.done")//
        .pollEnrich("file:/home/cpandey/Desktop/TestingCrypto/signed?fileName=${file:onlyname.noext.single}",5000, new MyAggregationStrategy())
        .to("crypto:verify:keyStoreParameters?keyStoreParameters=#signatureParams&alias=client&password=Welcome123")//
        .to("file:/home/cpandey/Desktop/TestingCrypto/verified");
    }
	
	private class MyAggregationStrategy implements AggregationStrategy {

	    @Override
	    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
	    	 final Object signatureBody = oldExchange.getIn().getBody();
             newExchange.getIn().setHeader("CamelDigitalSignature",
signatureBody);

             return newExchange;
	    }
	}
}



