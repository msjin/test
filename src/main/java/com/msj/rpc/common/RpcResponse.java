package com.msj.rpc.common;

public class RpcResponse<T> {

	private T result;
	
	private String exception;
	
	private String status= "00000";

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "RpcResponse [result=" + result + ", exception=" + exception + ", status=" + status + "]";
	}
	
	
}
