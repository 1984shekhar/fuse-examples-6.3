package com.mycompany.example;

import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;


public class Routes extends RouteBuilder {
    
    private Processor uriCreatorProcessor;
    private Processor fileProcessor;

    
    public void setUriCreatorProcessor(Processor uriCreatorProcessor) {
    
        this.uriCreatorProcessor = uriCreatorProcessor;
    }
    
    
    public void setFileProcessor(Processor fileProcessor) {
    
        this.fileProcessor = fileProcessor;
    }


    @Override
    public void configure() {

        from("quartz://polling-consumer?cron=0/1+*+*+*+*+?").routeId("polling-consumer")
            .log("Triggered")
            .process(fileProcessor);
    }
}
