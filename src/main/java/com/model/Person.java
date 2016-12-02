package com.model;

import java.io.Serializable;

public class Person implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	
	public int getId(){
		return this.id;
	}
	public void setId(int id){
		this.id = id;
	}
	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name = name;
	}
	
	public Person(){
		
	}
	
	public Person(int id,String name){
		this.id = id;
		this.name = name;
	}
}
