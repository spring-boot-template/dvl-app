package com.dvlcube.utils.interfaces.rest;

import com.dvlcube.app.repository.DvlJpaRepository;
import com.dvlcube.utils.interfaces.MxBean;
import org.springframework.http.ResponseEntity;

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
	ResponseEntity<Iterable<B>> getLike(String id);
}
