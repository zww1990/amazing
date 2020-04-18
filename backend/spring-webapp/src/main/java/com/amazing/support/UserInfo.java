package com.amazing.support;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.core.annotation.AliasFor;

/**
 * 自定义注解
 * 
 * @author home
 */
@Documented
@Retention(RUNTIME)
@Target(PARAMETER)
public @interface UserInfo {
	String DEFAULT_NAME = "User-Info";

	@AliasFor("name")
	String value() default DEFAULT_NAME;

	@AliasFor("value")
	String name() default DEFAULT_NAME;

	boolean required() default true;
}