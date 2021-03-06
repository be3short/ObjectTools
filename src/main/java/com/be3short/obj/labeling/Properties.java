package com.be3short.obj.labeling;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(
{ ElementType.FIELD })
public @interface Properties
{

	String label() default "";
}