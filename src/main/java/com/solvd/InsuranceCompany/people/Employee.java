package com.solvd.InsuranceCompany.people;

import com.solvd.InsuranceCompany.enums.EmployeeRole;

public class Employee extends Person {

	private String employeeHours;
	private EmployeeRole role;

	public Employee(
		String name,
		String lastName,
		String idNumber,
		String phoneNumber,
		String email,
		EmployeeRole role,
		String employeeHours
	) {
		super(name, lastName, idNumber, phoneNumber, email);
		this.role = role;
		this.employeeHours = employeeHours;
	}

	public EmployeeRole getRole() {
		return role;
	}

	public void setRole(EmployeeRole role) {
		this.role = role;
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
