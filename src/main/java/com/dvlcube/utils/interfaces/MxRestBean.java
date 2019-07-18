package com.dvlcube.utils.interfaces;

import java.io.Serializable;

/**
 * Bean that is directly used in rest services.
 * 
 * @since 25 de fev de 2019
 * @author Ulisses Lima
 */
public interface MxRestBean<S extends Serializable> extends MxBean<S> {
	/**
	 * @return class type.
	 * @since Jul 14, 2017
	 * @author Ulisses Lima
	 */
	Class<? extends MxBean<S>> classType();
}
