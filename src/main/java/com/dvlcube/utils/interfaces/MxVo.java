package com.dvlcube.utils.interfaces;

import java.io.Serializable;

/**
 * Marca uma classe como VO.
 * 
 * @since Jul 22, 2014
 * @author Ulisses
 */
public interface MxVo extends Serializable {
	/**
	 * @return nova instância do objeto.
	 * @since Jul 22, 2014
	 * @author Ulisses
	 */
	Object newInstance();
}