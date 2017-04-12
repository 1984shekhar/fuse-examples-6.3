package com.mycompany.example;

import java.util.Random;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;


public class UriCreatorProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {

        Random randomGen = new Random();
        //int random = randomGen.nextInt(1000000);
        String uri = "file:/home/cpandey/NotBackedUp/Development/Troubleshooting-Tool/SamuraiTool?fileName=test.txt";
        Message in = exchange.getIn();
        in.setBody(uri);
    }
}
