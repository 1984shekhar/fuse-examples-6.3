package com.redhat.propertyFabricTest;

import java.util.Dictionary;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.cm.ConfigurationAdmin;

public class ActivatorClass implements BundleActivator  {
	
	private ConfigurationAdmin configAdmin; 
	public static Dictionary<String, Object> configurations;

	@Override
	public void start(BundleContext context) throws Exception {
		// TODO Auto-generated method stub
		
		ServiceReference reference = context
                .getServiceReference(org.osgi.service.cm.ConfigurationAdmin.class.getName());	
		configAdmin = (ConfigurationAdmin)context.getService(reference);
		System.out.println(configAdmin.getConfiguration("myproperty").getProperties());
		configurations = (configAdmin.getConfiguration("myproperty").getProperties());
		//System.out.println(dict.get("result"));
	}

	@Override
	public void stop(BundleContext arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
