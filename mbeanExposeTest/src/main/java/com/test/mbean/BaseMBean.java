package com.test.mbean;


import javax.management.NotCompliantMBeanException;
import javax.management.StandardMBean;

public class BaseMBean extends StandardMBean implements Hello {

    public BaseMBean() throws NotCompliantMBeanException {
        super(Hello.class);
    }

	@Override
	public String hello() {
		return "Perfect operation is invoked as mbean";
	}
}