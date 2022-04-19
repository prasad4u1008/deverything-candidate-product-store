package com.deverything.candidate.productstore.pojo;

public final class ProductDimensionsObject {
	
	private final int status;
	private final int height;
	private final int width;

	/**
	 * @param status
	 * @param height
	 * @param width
	 */
	public ProductDimensionsObject(int status, int height, int width) {
		super();
		this.status = status;
		this.height = height;
		this.width = width;
	}

	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	@Override
	public String toString() {
		return "ProductDimensionsObject [height=" + height + ", width=" + width + "]";
	}
}
