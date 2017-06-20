package com.redhat.propertyFabricTest;

import java.io.IOException;
import java.util.Dictionary;

import org.osgi.service.cm.ConfigurationAdmin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class myPropertyClass {

	private static final Logger LOG = LoggerFactory.getLogger(myPropertyClass.class);
	private ConfigurationAdmin configAdmin;
	

	public ConfigurationAdmin getConfigAdmin() {
		return configAdmin;
	}

	public void setConfigAdmin(ConfigurationAdmin configAdmin) throws IOException {
		this.configAdmin = configAdmin;
		System.out.println(configAdmin.getConfiguration("myproperty").getProperties());
		Dictionary<String, Object> dict = configAdmin.getConfiguration("myproperty").getProperties();
		System.out.println(dict.get("result"));
	    
	}
	}
