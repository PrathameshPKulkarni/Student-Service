package net.texala.response.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestResponse<T> {

	private T data;
	private RestStatus<?> status;
	private RestCustom custom;

	public RestResponse() {
	}

	public RestResponse(final T data, final RestStatus<?> status) {
		this.data = data;
		this.status = status;
	}

	public RestResponse(final T data) {
		this.data = data;
	}

	public RestResponse(final T data, final RestStatus<?> status, final RestCustom custom) {
		this.data = data;
		this.status = status;
		this.custom = custom;
	}
}