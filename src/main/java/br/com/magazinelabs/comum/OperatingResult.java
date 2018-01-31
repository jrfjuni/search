package br.com.magazinelabs.comum;

import java.util.Map;

public class OperatingResult {
	
	private Boolean success;
	
	private Object object;
	
	private Map<String, Object> extraData;
	
	public OperatingResult(){}
	
	public OperatingResult(Boolean success){
		this.success = success;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public Map<String, Object> getExtraData() {
		return extraData;
	}

	public void setExtraData(Map<String, Object> extraData) {
		this.extraData = extraData;
	}
}