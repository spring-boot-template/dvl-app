package com.dvlcube.app.manager.data.interfaces;

/**
 * @since 5 de mar de 2019
 * @author Ulisses Lima
 */
public interface Hashable {

	/**
	 * @return hash
	 * @since 5 de mar de 2019
	 * @author Ulisses Lima
	 */
	String getHash();

	/**
	 * @since 5 de mar de 2019
	 * @author Ulisses Lima
	 */
	void genUuid();

	/**
	 * @return UUID
	 * @since 5 de mar de 2019
	 * @author Ulisses Lima
	 */
	String uuid();
}
