package com.excel.utility.with_annotation;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.excel.utility.models.Student;

public class GenerateExcelReportTest {
	
	
	@Test
	public void test_generator(){
		List<Student> studentList = new ArrayList<>();
		studentList.add(new Student(12345, "Priyaranjan", "Behera", "7th", 90.82));
		studentList.add(new Student(12345, "biplab", "pati", "6th", 98.82));
		GenerateExcelReport<Student> generator = new GenerateExcelReport<>();
		generator.createExcel(studentList);
	}
}
