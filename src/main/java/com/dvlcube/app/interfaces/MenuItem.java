package com.dvlcube.app.interfaces;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.dvlcube.app.manager.data.e.Menu;

/**
 * Represents a menu item.
 * 
 * @since 20 de fev de 2019
 * @author Ulisses Lima
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface MenuItem {
	/**
	 * @return true if this controller does not allow insertions
	 * @since 25 de fev de 2019
	 * @author Ulisses Lima
	 */
	boolean readOnly() default false;

	/**
	 * @return true if this view should be filtered by the logged in user
	 * @since 1 de abr de 2019
	 * @author Ulisses Lima
	 */
	boolean userSpecific() default false;

	/**
	 * @return true if this view should only by accessible by admins
	 * @since 1 de abr de 2019
	 * @author Ulisses Lima
	 */
	boolean adminOnly() default false;

	/**
	 * @return group ID
	 * @since 17 de abr de 2019
	 * @author Ulisses Lima
	 */
	Menu value() default Menu.MISC;
}
