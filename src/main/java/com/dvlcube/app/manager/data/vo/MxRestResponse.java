package com.dvlcube.app.manager.data.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.dvlcube.utils.interfaces.MxVo;

/**
 * @since 26 de fev de 2019
 * @author Ulisses Lima
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class MxRestResponse implements MxVo {
	private static final long serialVersionUID = 1L;
	private MxRestError error;

	public MxRestResponse() {
	}

	public MxRestResponse(int status, String key, String message) {
		this.error = new MxRestError(status, key, message);
	}

	public MxRestError getError() {
		return error;
	}

	public void setError(MxRestError error) {
		this.error = error;
	}

	/**
	 * @param status
	 * @param key
	 * @param message
	 * @return error.
	 * @since 26 de fev de 2019
	 * @author Ulisses Lima
	 */
	public void error(int status, String key, String message) {
		this.error = new MxRestError(status, key, message);
	}
}
