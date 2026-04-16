package com.solvd.InsuranceCompany.people;

import java.util.Objects;

public abstract class Person {

	private String name;
	private String lastName;
	private String idNumber;
	private String phoneNumber;
	private String email;

	public Person(String name, String lastName, String idNumber, String phoneNumber, String email) {
		this.name = name;
		this.lastName = lastName;
		this.idNumber = idNumber;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass()) return false;
		Person person = (Person) o;
		return Objects.equals(getName(), person.getName()) && Objects.equals(getLastName(), person.getLastName());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getName(), getLastName());
	}
}
