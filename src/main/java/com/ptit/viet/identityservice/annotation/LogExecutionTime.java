package com.ptit.viet.identityservice.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD) // applied only to method
@Retention(RetentionPolicy.RUNTIME) // available at runtime
public @interface LogExecutionTime {

}
