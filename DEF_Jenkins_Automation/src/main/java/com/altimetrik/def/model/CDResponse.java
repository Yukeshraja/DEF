package com.altimetrik.def.model;

import java.util.List;
import java.util.Map;

public class CDResponse {

	private long timestamp;
	private String status;
	/*private String errorCode;
	private String errorDescription;*/
	private String errorMessage;
	private List<CDModel> result;
	
	private Map<String, String> error;

	/**
	 * @return the timestamp
	 */
	public long getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @param errorMessage the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * @return the result
	 */
	public List<CDModel> getResult() {
		return result;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(List<CDModel> result) {
		this.result = result;
	}

	/**
	 * @return the error
	 */
	public Map<String, String> getError() {
		return error;
	}

	/**
	 * @param error the error to set
	 */
	public void setError(Map<String, String> error) {
		this.error = error;
	}
}
