package com.dvlcube.app.jpa;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import com.dvlcube.utils.interfaces.MxBean;

@NoRepositoryBean
public interface BasicRepository<T extends MxBean<?>, ID> {
	/**
	 * @param id
	 * @return Collection<T>
	 * @since 29 de abr de 2019
	 * @author Ulisses Lima
	 */
	@Query
	Collection<T> findAllByIdContaining(ID id);
}
