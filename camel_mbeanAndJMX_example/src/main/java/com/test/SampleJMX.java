/**
 * 
 */
package com.test;

import io.fabric8.api.jmx.FabricManagerMBean;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.management.JMX;
import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

public class SampleJMX {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SampleJMX sampleJMX = new SampleJMX();

		List<String> output;
		try {
			output = sampleJMX.getContainerIds();

			System.out.println(output);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private JMXConnector getConnector() throws MalformedURLException,
			IOException {
		String[] creds = new String[2];
		creds[0] = "admin";
		creds[1] = "admin";

		Map<String, String[]> env = new HashMap<String, String[]>();
		env.put(JMXConnector.CREDENTIALS, creds);
		JMXServiceURL url = new JMXServiceURL(
				"service:jmx:rmi://0.0.0.0:44444/jndi/rmi://0.0.0.0:1099/karaf-root");
		return JMXConnectorFactory.newJMXConnector(url, env);

	}

	private FabricManagerMBean getMbeanProxy(JMXConnector connector) {

		FabricManagerMBean mBeanProxy = null;

		try {

			MBeanServerConnection connection = connector
					.getMBeanServerConnection();

			ObjectName fabricObjectName = new ObjectName(
					"io.fabric8:type=Fabric");
			mBeanProxy = JMX.newMBeanProxy(connection, fabricObjectName,
					FabricManagerMBean.class, true);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return mBeanProxy;
	}

	public List<String> getContainerIds() throws Exception {

		JMXConnector connector = getConnector();

		connector.connect();
		FabricManagerMBean mbeanProxy = getMbeanProxy(connector);
		List<String> containerIds = getContainerIds(mbeanProxy);

		System.out.println("Number of container's = " + containerIds.size());

		connector.close();

		return containerIds;

	}

	private List<String> getContainerIds(FabricManagerMBean mbeanProxy) {

		List<String> containerIdList = new ArrayList<String>();
		try {

			List<Map<String, Object>> containerList = mbeanProxy
					.containers(Arrays.asList("id"));
			for (Map<String, Object> map : containerList) {
				if (map.containsKey("id")) {
					containerIdList.add((String) map.get("id"));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return containerIdList;

	}

}
