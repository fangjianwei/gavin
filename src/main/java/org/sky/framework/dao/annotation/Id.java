package org.sky.framework.dao.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.sky.framework.dao.id.IdAutoIncrement;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Id {
	public String type() default "";
	
	public Class<?> clazz() default IdAutoIncrement.class;
}
