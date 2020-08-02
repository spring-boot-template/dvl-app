package com.dvlcube.utils.interfaces;

import java.io.Serializable;
import java.util.Map;

import com.dvlcube.app.manager.data.interfaces.MultiId;

/**
 * Para beans com ID composto
 * 
 * @see MultiId
 * @param <S>
 * @param <T>
 * @since 26 de mar de 2019
 * @author Ulisses Lima
 */
public interface MxMultiIdBeanService<T extends MxBean<?>, S extends Serializable> extends MxBeanService<T, S> {

	/**
	 * @param params request params
	 * @param id
	 * @return list or single item
	 * @since 26 de mar de 2019
	 * @author Ulisses Lima
	 */
	Object getAllOrOne(Map<String, String> params, S id);
}
