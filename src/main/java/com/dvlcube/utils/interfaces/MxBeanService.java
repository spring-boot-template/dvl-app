package com.dvlcube.utils.interfaces;

import java.io.Serializable;
import java.util.Map;
import java.util.Optional;

import com.dvlcube.app.manager.data.vo.MxRestResponse;

/**
 * Marker interface for REST service classes.
 * 
 * @since 14 de fev de 2019
 * @author Ulisses Lima
 */
public interface MxBeanService<T extends MxBean<?>, S extends Serializable> {
	/**
	 * Traz todas as entidades salvas.
	 * 
	 * @param params request params
	 * @return T
	 * @since 20 de fev de 2019
	 * @author Ulisses Lima
	 */
	Iterable<T> get(Map<String, String> params);

	/**
	 * @param id
	 * @return item by id
	 * @since 8 de mar de 2019
	 * @author Ulisses Lima
	 */
	Optional<T> get(S id);

	/**
	 * Persiste uma nova instância de uma entidade.
	 * 
	 * @param body T
	 * @return OK
	 * @since 20 de fev de 2019
	 * @author Ulisses Lima
	 */
	MxRestResponse post(T body);
}
