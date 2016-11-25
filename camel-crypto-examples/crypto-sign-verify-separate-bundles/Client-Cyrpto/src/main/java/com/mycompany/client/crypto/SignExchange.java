package com.mycompany.client.crypto;



import org.apache.camel.builder.RouteBuilder;

public class SignExchange extends RouteBuilder {

    /**
     * Let's configure the Camel routing rules using Java code...
     */
	@Override
    public void configure() {

        System.out.println("......Inside MyRouteBuilder.....");
        from("file:/home/cpandey/Desktop/TestingCrypto/unsigned?move=../signed")//
        .to("crypto:sign:keyStoreParameters?keyStoreParameters=#signatureParams&alias=client&password=Welcome123")//
        .setBody(header("CamelDigitalSignature")).to("file:/home/cpandey/Desktop/TestingCrypto/signed?fileName=${file:name}.signature");
    }
}
