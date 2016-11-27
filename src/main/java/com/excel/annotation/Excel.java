package com.excel.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import com.excel.annotation.ExcelStyle;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Excel {
	String name() default ""; 
	String sheetName() default ""; 
	ExcelStyle style() default @ExcelStyle;
	String dir() default "C://Users//iPINTU";
}
