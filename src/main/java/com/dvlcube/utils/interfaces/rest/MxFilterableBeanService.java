package com.dvlcube.utils.interfaces.rest;

import java.io.Serializable;

import com.dvlcube.utils.interfaces.MxBean;
import com.dvlcube.utils.interfaces.MxBeanService;

/**
 * @param <T>
 * @param <S>
 * @since 2 de mai de 2019
 * @author Ulisses Lima
 */
public interface MxFilterableBeanService<T extends MxBean<?>, S extends Serializable>
		extends MxBeanService<T, S>, FilterableService<T> {
}
