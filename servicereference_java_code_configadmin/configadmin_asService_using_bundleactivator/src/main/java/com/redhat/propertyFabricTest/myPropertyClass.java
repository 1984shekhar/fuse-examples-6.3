package com.redhat.propertyFabricTest;

import java.io.IOException;
import java.util.Dictionary;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.osgi.service.cm.ConfigurationAdmin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class myPropertyClass implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub
System.out.println("Accessing properties: "+ActivatorClass.configurations.get("result"));
	}

}
