package com.dvlcube.app.interfaces;

import com.dvlcube.app.repository.DvlJpaRepository;

/**
 * @since 2 de mai de 2019
 * @author Ulisses Lima
 */
public interface FilterableService<B extends MxBean<?>> {

	/**
	 * Get all B's with id like id.
	 * 
	 * @see DvlJpaRepository
	 * @param id
	 * @return List<B>
	 * @since 2 de mai de 2019
	 * @author Ulisses Lima
	 */
	Iterable<B> getLike(String id);
}
