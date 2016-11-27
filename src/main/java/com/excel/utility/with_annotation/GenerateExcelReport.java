package com.excel.utility.with_annotation;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.StringUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.excel.annotation.Excel;
import com.excel.annotation.ExcelColumn;
import com.excel.annotation.ExcelStyle;
import com.google.common.base.CaseFormat;

public class GenerateExcelReport<T> {
	
	private Workbook workbook;
	private Sheet sheet;
	private Row currentRow;
	private Cell currentCell;
	private CellStyle headerCellStyle;
	private Font headerFont;
	
	
	private String path;
	private String fileName;
	private Integer rowIndex;
	
	/** 
	 * Constructor takes file path  and file name as input.
	 * Initializes workbook,textStyle,headerCellStyle,headerFont
	 */	
	public GenerateExcelReport() {
		super();
		rowIndex=0;
	}

	/** 
	 *Takes List of Any pojo as input.
	 *Iterate through each elements and sets all the value in excel rows.
	 */
	public void createExcel(List<T> objects){
		if(objects==null || objects.isEmpty()){
			System.out.println("Input data is Empty , Please provide some proper data..................");
			return;
		}
		Class<? extends Object> claszz = objects.get(0).getClass();
		Excel excel =claszz.getDeclaredAnnotation(Excel.class);
		if(excel==null){
			throw new IllegalArgumentException("Class does not have Excel annotation");
		}
		this.path = excel.dir();	
		String fileName = excel.name();
		if(fileName.isEmpty())  fileName = claszz.getSimpleName();
		this.fileName = fileName;
		
		String sheetName  = excel.sheetName();
		if(sheetName.isEmpty()) sheetName = claszz.getSimpleName();
		
		if(this.fileName.contains(".xls")){
			workbook = new HSSFWorkbook();
		}else if (fileName.contains(".xlsx")){
			workbook = new XSSFWorkbook();
		}else{
			this.fileName = this.fileName.concat(".xls");
			workbook = new HSSFWorkbook();
		}
		
		headerCellStyle = workbook.createCellStyle();
		headerFont = workbook.createFont();

		
		ExcelStyle excelStyle = excel.style();

		if(excelStyle!=null){
			if(excelStyle.boldHeader());
				headerFont.setBoldweight((short)0);
			if(excelStyle.mergeColumn()){
				
			}
		}
		
		headerCellStyle.setFont(headerFont);
		
		Field[] fields = claszz.getDeclaredFields();
		sheet = workbook.createSheet(sheetName);

		//Create header
		currentRow = sheet.createRow(rowIndex++);
		Arrays.stream(fields).forEach(this::createHeader);
		
		//Create Columns
		objects.forEach(this::createCurrentRow);
		
		this.saveAndClose();
		
		
	}
	/** 
	 * Fills data in the current Row;
	 */
	private void createCurrentRow(T object){
		currentRow = sheet.createRow(rowIndex++);
		currentCell=null;
		Arrays.stream(object.getClass().getDeclaredFields())
		.forEach(filed->{
			try {
				filed.setAccessible(true);
				currentCell =currentRow.createCell(currentCell==null?0:currentCell.getColumnIndex()+1);
				Object value =filed.get(object);
				if(String.class.equals(filed.getType())){
					currentCell.setCellValue((String)value);
				}else if(Integer.class.equals(filed.getType())||int.class.equals(filed.getType())){
					currentCell.setCellValue((Integer)value);
				}else if(Double.class.equals(filed.getType())||double.class.equals(filed.getType())){
					currentCell.setCellValue((Double)value);
				}else if(Long.class.equals(filed.getType())||Long.class.equals(filed.getType())){
					currentCell.setCellValue((Long)value);
				}else{
					currentCell.setCellValue(value.toString());
				}
			} catch (Exception e) {
				System.out.println("Caught exception while reading "+filed.getName()+" from object"+object);
			}
		});
	}	
	
	/** 
	 *Creates Header of the excel. 
	 */
	private void createHeader(Field headerName){
		ExcelColumn column= headerName.getDeclaredAnnotation(ExcelColumn.class);
		if(column!=null){
			currentCell =currentRow.createCell(currentCell==null?0:currentCell.getColumnIndex()+1);
			String value = column.name();
			if(value.isEmpty()) value = headerName.getName();
			currentCell.setCellValue(value);
			currentCell.setCellStyle(headerCellStyle);
		}
	}
	
	
	/** 
	 * Writes excel file to physical directory.
	 */
	private void saveAndClose(){
		currentRow=null;
		currentCell=null;
		sheet =null;
		File directory = new File(path);
		File excelFile = new File(directory,fileName);
		try(FileOutputStream fos = new FileOutputStream(excelFile)){
			workbook.write(fos);
			workbook=null;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	/** 
	 *
	 */
	public CellStyle getHeaderCellStyle() {
		return headerCellStyle;
	}

	/**
	 * For more customization header style can be cloned 
	 */
	public void setHeaderCellStyle(CellStyle headerCellStyle) {
		this.headerCellStyle.cloneStyleFrom(headerCellStyle);
	}


}
