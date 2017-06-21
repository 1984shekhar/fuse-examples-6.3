package com.redhat.propertyFabricTest;

import java.io.IOException;
import java.util.Dictionary;

import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;
import org.osgi.service.cm.ConfigurationAdmin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class myPropertyClass {

	private static final Logger LOG = LoggerFactory.getLogger(myPropertyClass.class);

	
	public void someTest() throws IOException{
		BundleContext context = FrameworkUtil.getBundle(this.getClass()).getBundleContext();
	    ServiceReference serviceReference = context.getServiceReference(org.osgi.service.cm.ConfigurationAdmin.class.getName());
	    ConfigurationAdmin configAdmin = (ConfigurationAdmin)context.getService(serviceReference);
	    Dictionary<String, Object> dict = configAdmin.getConfiguration("myproperty").getProperties();
	    System.out.println(dict.get("result"));
	    /*Hashtable<String, Object> properties = new Hashtable<String, Object>();
		properties.put("result", "HiHI"); 
		context.registerService(ManagedService.class.getName(), new ConfigUpdater() , properties);*/
	   
	}
	


/*private final class ConfigUpdater implements ManagedService {
	@Override
	public void updated(Dictionary config) throws ConfigurationException {
		if (config == null) { return; }
		//Global.printInfo(getClass(), "Property file updated: "+config.get("service.pid"));
			
		String servicePID = (String) config.get("service.pid");
		System.out.println(servicePID);
		//Update configurations
	}
}*/
}
