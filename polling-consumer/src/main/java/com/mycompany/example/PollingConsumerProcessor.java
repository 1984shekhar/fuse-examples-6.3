package com.mycompany.example;

import org.apache.camel.ConsumerTemplate;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class PollingConsumerProcessor implements Processor {

    private static final Logger LOGGER = LoggerFactory.getLogger(PollingConsumerProcessor.class);

    private ConsumerTemplate consumerTemplate;
    private Long timeout = 500L;


    public void setConsumerTemplate(ConsumerTemplate consumerTemplate) {
    
        this.consumerTemplate = consumerTemplate;
    }


    public void setTimeout(Long timeout) {

        this.timeout = timeout;
    }


    @Override
    public void process(Exchange exchange) throws Exception {

        Message in = exchange.getIn();
        String uri = in.getBody(String.class);
        LOGGER.info("Polling URI: " + uri);
        Object body = consumerTemplate.receiveBody("file:/home/cpandey/NotBackedUp/Development/Troubleshooting-Tool/SamuraiTool?fileName=test.txt", timeout);
        LOGGER.info("Got data: " + body);
        in.setBody(body);
    }
}
