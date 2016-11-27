package com.excel.utility.models;

import com.excel.annotation.Excel;
import com.excel.annotation.ExcelColumn;
import com.excel.annotation.ExcelStyle;

@Excel(name="Student_Details.xls",sheetName="Students",dir="E:/test"
,style=@ExcelStyle())
public class Student {
	
	@ExcelColumn(name="ROLL NUMBER")
	private long rollNumber;
	
	@ExcelColumn(name="FIRST NAME")
	private String firstName;
	
	@ExcelColumn(name="LAST NAME")
	private String lastName;
	
	@ExcelColumn(name="GRADE")
	private String grade;
	
	@ExcelColumn(name="MARKS (PERCENTAGE)")
	private double percentage;
	
	
	
	public Student(long rollNumber, String firstName, String lastName,
			String grade, double percentage) {
		super();
		this.rollNumber = rollNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.grade = grade;
		this.percentage = percentage;
	}
	public long getRollNumber() {
		return rollNumber;
	}
	public void setRollNumber(long rollNumber) {
		this.rollNumber = rollNumber;
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
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public double getPercentage() {
		return percentage;
	}
	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}
	
	@Override
	public String toString() {
		return "Student [rollNumber=" + rollNumber + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", grade=" + grade
				+ ", percentage=" + percentage + "]";
	}	
}
