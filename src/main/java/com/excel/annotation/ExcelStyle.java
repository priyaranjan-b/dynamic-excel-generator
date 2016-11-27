package com.excel.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
public @interface ExcelStyle {
	
	String fontName() default "";
	boolean boldHeader() default false;
	String fontSize() default "";
	boolean mergeColumn() default false;
	boolean hideGrid() default false;
	
}
