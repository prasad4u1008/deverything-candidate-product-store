package com.deverything.candidate.productstore.pojo;

public class CheckoutSummaryObject {
	
	private final String statusCode;
	private final String result;
	/**
	 * @param statusCode
	 * @param result
	 */
	public CheckoutSummaryObject(String statusCode, String result) {
		super();
		this.statusCode = statusCode;
		this.result = result;
	}
	/**
	 * @return the statusCode
	 */
	public String getStatusCode() {
		return statusCode;
	}
	/**
	 * @return the result
	 */
	public String getResult() {
		return result;
	}
	@Override
	public String toString() {
		return "CheckoutSummaryObject [statusCode=" + statusCode + ", result=" + result + "]";
	}

}
