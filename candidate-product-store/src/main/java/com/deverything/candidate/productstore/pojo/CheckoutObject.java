package com.deverything.candidate.productstore.pojo;

import java.util.Collections;
import java.util.List;

public final class CheckoutObject {

	private final int boxId;
	private final List<Integer> productIds;
	
	/**
	 * @param boxId
	 * @param productIds
	 */
	public CheckoutObject(int boxId, List<Integer> productIds) {
		super();
		this.boxId = boxId;
		this.productIds = productIds;
	}

	/**
	 * @return the boxId
	 */
	public int getBoxId() {
		return boxId;
	}

	/**
	 * @return the productIds
	 */
	public List<Integer> getProductIds() {
		return Collections.unmodifiableList(productIds);
	}





	@Override
	public String toString() {
		return "CheckoutObject [boxId=" + boxId + ", productIds=" + productIds + "]";
	}
	
	

}
