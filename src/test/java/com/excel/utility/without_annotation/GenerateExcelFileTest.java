package com.excel.utility.without_annotation;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.excel.utility.models.Employee;
import com.excel.utility.with_annotation.GenerateExcelReport;
import com.google.common.base.CaseFormat;

public class GenerateExcelFileTest {
	
	
	@Test
	public void test(){
		Employee e = new Employee("Priyaranjan", "B", 106221, "Production", 50000000.00);
		List<Employee> employeeList = new ArrayList<Employee>();
		employeeList.add(e);
		GenerateExcelFile<Employee> generator = new GenerateExcelFile<>();
		generator.setHeaderTextStyle(CaseFormat.UPPER_UNDERSCORE);
		generator.isHeaderBold(true);
		generator.createExcel(employeeList);
	}

}
