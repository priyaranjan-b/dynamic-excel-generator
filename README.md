# dynamic-excel-generator

Its generic utility which generates excel file dinamically irrespective of type (POJO)

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

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


