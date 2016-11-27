package com.excel.utility.models;

public class Employee {
	
	private String firstName;
	private String lastName;
	private long empID;
	private String department;
	private double salary;
	
	
	public Employee() {
		
	}
	public Employee(String firstName, String lastName, long empID,
			String department, double salary) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.empID = empID;
		this.department = department;
		this.salary = salary;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public long getEmpID() {
		return empID;
	}
	public void setEmpID(long empID) {
		this.empID = empID;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	@Override
	public String toString() {
		return "Employee [firstName=" + firstName + ", lastName=" + lastName
				+ ", empID=" + empID + ", department=" + department
				+ ", salary=" + salary + "]";
	}
	
	

}
