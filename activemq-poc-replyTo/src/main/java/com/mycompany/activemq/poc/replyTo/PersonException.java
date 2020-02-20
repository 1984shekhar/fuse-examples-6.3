package com.mycompany.activemq.poc.replyTo;

import javax.xml.ws.WebFault;

@WebFault(name="PersonException")
public class PersonException extends RuntimeException {
    private final String personid;
    public PersonException(String message, String personId){
        super(message);
        this.personid = personId;
    }

    public String getPersonid(){
        return this.personid;
    }


}
