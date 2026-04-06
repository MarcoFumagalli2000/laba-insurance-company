package com.solvd.InsuranceCompany.People;

public class Employee extends Person {

	private String employeeRole;
	private String employeeHours;

	public Employee(
		String name,
		String lastName,
		String idNumber,
		String phoneNumber,
		String email,
		String employeeRole,
		String employeeHours
	) {
		super(name, lastName, idNumber, phoneNumber, email);
		this.employeeRole = employeeRole;
		this.employeeHours = employeeHours;
	}

	public String getEmployeeRole() {
		return employeeRole;
	}

	public void setEmployeeRole(String employeeRole) {
		this.employeeRole = employeeRole;
	}

	public String getEmployeeHours() {
		return employeeHours;
	}

	public void setEmployeeHours(String employeeHours) {
		this.employeeHours = employeeHours;
	}

	@Override
	public String toString() {
		return this.getName() + " " + this.getLastName() + ", email: " + this.getEmail() + " ID" + this.getIdNumber();
	}
}
