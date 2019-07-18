package com.dvlcube.utils.annotations.sql;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Defines the property to use on "like" statements.
 * 
 * @since 2 de mai de 2019
 * @author Ulisses Lima
 */
@Target(TYPE)
@Retention(RUNTIME)
public @interface Like {

	/**
	 * @return "like" property
	 * @since 2 de mai de 2019
	 * @author Ulisses Lima
	 */
	String value() default "name";
}
