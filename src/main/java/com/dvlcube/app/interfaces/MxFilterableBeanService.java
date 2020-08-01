package com.dvlcube.app.interfaces;

import java.io.Serializable;

/**
 * @param <T>
 * @param <S>
 * @since 2 de mai de 2019
 * @author Ulisses Lima
 */
public interface MxFilterableBeanService<T extends MxBean<?>, S extends Serializable>
		extends MxBeanService<T, S>, FilterableService<T> {
}
