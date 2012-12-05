package org.sky.framework.dao.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Ref {
	
	public String refName();
	
	public String[] refKey();
	
	public String[] targetKey();

	public String otherConditions() default "";
}
