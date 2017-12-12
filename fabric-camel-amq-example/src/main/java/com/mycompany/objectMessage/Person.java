package com.mycompany.objectMessage;
 
import java.io.Serializable;
 
public class Person implements Serializable
{

     
    /**
	 * 
	 */
	private static final long serialVersionUID = 4671754708253223570L;
	private String name;
    private int age;
 
    public Person(String name, int age)
    {
        super();
        this.name = name;
        this.age = age;
    }
 
    public Person() {
		// TODO Auto-generated constructor stub
	}

	public String getName()
    {
        return name;
    }
 
    public void setName(String name)
    {
        this.name = name;
    }
 
    public int getAge()
    {
        return age;
    }
 
    public void setAge(int age)
    {
        this.age = age;
    }
 
    @Override
    public String toString()
    {
        return String.format("Person [name=%s, age=%s]", name, age);
    }
 
}