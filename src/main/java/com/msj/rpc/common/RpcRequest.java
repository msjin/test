package com.msj.rpc.common;

import java.util.Arrays;

public class RpcRequest {
private String serviceId;
	
	private String command;
	
	private Object[] params;

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public Object[] getParams() {
		return params;
	}

	public void setParams(Object[] params) {
		this.params = params;
	}

	@Override
	public String toString() {
		return "RpcResponse [serviceId=" + serviceId + ", command=" + command + ", params=" + Arrays.toString(params)
				+ "]";
	}
}
