package com.dvlcube.app.manager.data.interfaces;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * For objects with compound IDs.
 * 
 * @since 25 de mar de 2019
 * @author Ulisses Lima
 */
public interface MultiId {

	/**
	 * @return compound ID
	 * @since 25 de mar de 2019
	 * @author Ulisses Lima
	 */
	@JsonIgnore
	String uuid();

	/**
	 * @return id name. Used for "id like" queries
	 * @since 29 de abr de 2019
	 * @author Ulisses Lima
	 */
	String getName();
}
