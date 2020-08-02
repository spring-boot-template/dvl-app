package com.dvlcube.app.manager.data.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.dvlcube.utils.interfaces.MxVo;

/**
 * @since 26 de fev de 2019
 * @author Ulisses Lima
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MxRestError implements MxVo {
	private static final long serialVersionUID = 1L;

	private int status;
	private String code;
	private String message;

	public MxRestError() {
	}

	public MxRestError(int status, String code, String message) {
		this.status = status;
		this.code = code;
		this.message = message;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public MxRestError newInstance() {
		return new MxRestError();
	}
}
