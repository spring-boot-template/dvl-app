package com.dvlcube.utils.interfaces;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Marker interface.
 * 
 * @since 13 de fev de 2019
 * @author Ulisses Lima
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public interface MxBean<S extends Serializable> extends Identifiable<S> {

	/**
	 * @return id
	 * @since 7 de mar de 2019
	 * @author Ulisses Lima
	 */
	S getId();
}
