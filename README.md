# dynamic-excel-generator

Its a generic utility which generates excel file dinamically irrespective of type (POJO).

### Prerequisites

Dependency required are : apache POI, Google Guava


## How to use
In the project there are 2 type of implementation present.

### 1 : Without Annotation - This implementation will allow any kind of POJO to generate excel sheet.
  ```
		Employee e = new Employee("Priyaranjan", "B", 106221, "Production", 50000000.00);
		List<Employee> employeeList = new ArrayList<Employee>();
		employeeList.add(e);
		GenerateExcelFile<Employee> generator = new GenerateExcelFile<>();
		generator.setHeaderTextStyle(CaseFormat.UPPER_UNDERSCORE);
		generator.isHeaderBold(true);
		generator.createExcel(employeeList);
```
Here every customization fileds are  is optional only use if customazation is required.  such as you don't have to write below code if you want default behaviour.
```
		generator.setHeaderTextStyle(CaseFormat.UPPER_UNDERSCORE);
		generator.isHeaderBold(true);
```
If user want to store file in some specified location in the default directory. Then use parameterized constructor instead of default constructor.

```
	GenerateExcelFile<Employee> generator = new GenerateExcelFile<>("C:\user\username\directory_name", "file_name.xls");
```

### 2 : With Annotation - This implementation will allow only POJO having @Excel annotation to generate excel sheet.
If the POJO doesn't contain @Excel annotation this utility will do nothing
Similarly all the fields should be annotated with @ExcelColumn. (Those fields doesn't contain @ExcelColumn anootation will be skipped)
All the parameter of above 2 annotation are optional. Give parameter value if you don't want the default behaviour.
Below is the syntax of POJO
  ```
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
```
#### @Excel
	If name is not provided then the file name will be same as class name. 
	If dir value  is not provided the it will use the default directory as specified in the @Excel annotation.
#### @ExcelColumn
	if name is not provided it will take filed name as header name.
#### @ExcelStyle
	With this annotation you can customize excel styling like FONT, FONT SIZE , bold header font, hiding grid and may mode.
	All the params are optional.

Here is how to use annotation based excel report generator
```
		List<Student> studentList = new ArrayList<>();
		studentList.add(new Student(12345, "Priyaranjan", "Behera", "7th", 90.82));
		studentList.add(new Student(12345, "biplab", "pati", "6th", 98.82));
		GenerateExcelReport<Student> generator = new GenerateExcelReport<>();
		generator.createExcel(studentList);
```
