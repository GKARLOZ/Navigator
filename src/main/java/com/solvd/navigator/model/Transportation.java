package com.solvd.navigator.model;

public class Transportation {
	private Long id;
	private String name;
	private Driver driver;

	public Transportation() {
		this.driver = new Driver();
	}
	public Transportation(String name){
		this.driver = new Driver();
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	@Override
	public String toString() {
		return "Transportation{" +
				"id=" + id +
				", name='" + name + '\'' +
				", driver=" + driver+
				'}';
	}
}
