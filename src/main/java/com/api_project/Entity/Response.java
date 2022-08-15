package com.api_project.Entity;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;

public class Response<T> extends HttpEntity<T>{
	
	@Nullable
	private HttpStatus status;
	
	@Nullable
	private T body;
	
	public Response(@Nullable HttpStatus status, @Nullable T body) {
		this.status = status;
	
		this.body = body;
	}

	public Response() {
		// TODO Auto-generated constructor stub
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	
	public T getBody() {
		return body;
	}

	public void setBody(T body) {
		this.body = body;
	}
	
}
