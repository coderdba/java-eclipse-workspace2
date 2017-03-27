package com.maven.camel.restFromToExample1;

public class TheObject {

	private int id;
	private String name;

	public TheObject() {

	}

	public TheObject(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}
