package com.dvlcube.app.rest;

import java.io.Serializable;

import com.dvlcube.app.manager.data.vo.MxRestResponse;

/**
 * @since 8 de mar de 2019
 * @author Ulisses Lima
 */
public class GenericRestResponse extends MxRestResponse {
	private static final long serialVersionUID = 1L;

	private Serializable id;

	public GenericRestResponse() {
	}

	public GenericRestResponse(Serializable id) {
		this.id = id;
	}

	@Override
	public Object newInstance() {
		return new GenericRestResponse();
	}

	/**
	 * @return ok
	 * @since 8 de mar de 2019
	 * @author Ulisses Lima
	 */
	public static MxRestResponse ok() {
		return new GenericRestResponse();
	}

	/**
	 * @param id
	 * @return ok with id
	 * @since 8 de mar de 2019
	 * @author Ulisses Lima
	 */
	public static MxRestResponse ok(Serializable id) {
		return new GenericRestResponse(id);
	}

	/**
	 * @return error message
	 * @since 8 de mar de 2019
	 * @author Ulisses Lima
	 */
	public static MxRestResponse error(String message) {
		GenericRestResponse response = new GenericRestResponse();
		response.error(409, "rest.conflict", message);
		return response;
	}

	public Serializable getId() {
		return id;
	}

	public void setId(Serializable id) {
		this.id = id;
	}
}
