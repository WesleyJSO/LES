package br.com.les.backend.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EmployeeQuery {
	String name() default "";
	String comparator() default " = ";
	String multipleSelector() default " and ";
}
