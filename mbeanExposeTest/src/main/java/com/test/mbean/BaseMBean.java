package com.test.mbean;


import java.security.AccessControlContext;
import java.security.AccessController;
import java.util.Set;

import javax.management.NotCompliantMBeanException;
import javax.management.StandardMBean;
import javax.security.auth.Subject;
import org.apache.karaf.jaas.boot.principal.UserPrincipal;

public class BaseMBean extends StandardMBean implements Hello {

    public BaseMBean() throws NotCompliantMBeanException {
        super(Hello.class);
    }

	@Override
	public String hello() {
		
		AccessControlContext acc = AccessController.getContext();
	    Subject subject = Subject.getSubject(acc);
	    if (subject != null)  {
	    Set<UserPrincipal> principals = subject.getPrincipals(UserPrincipal.class);
	    try{
	    UserPrincipal principal = principals.iterator().next();
	    System.out.println("Username: "+principal.getName());
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
	    }
		return "Perfect operation is invoked as mbean";
	}
}