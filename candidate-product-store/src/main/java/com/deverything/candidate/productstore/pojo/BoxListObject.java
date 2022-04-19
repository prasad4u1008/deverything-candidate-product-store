package com.deverything.candidate.productstore.pojo;

import java.util.Collections;
import java.util.List;

public class BoxListObject {
	
	private final List<BoxObject> boxes;
	private final int statusCode;
	
	/**
	 * @param boxes
	 * @param statusCode
	 */
	public BoxListObject(List<BoxObject> boxes, int statusCode) {
		super();
		this.boxes = boxes;
		this.statusCode = statusCode;
	}


	/**
	 * @return the boxes
	 */
	public List<BoxObject> getBoxes() {
		return Collections.unmodifiableList(boxes);
	}


	/**
	 * @return the statusCode
	 */
	public int getStatusCode() {
		return statusCode;
	}


	@Override
	public String toString() {
		return "BoxListObject [Available Boxes =" + boxes + "]";
	}
	
	
   public class BoxObject{
    	private final int id;
    	private final int height;
    	private final int width;
		
		/**
		 * @param id
		 * @param height
		 * @param width
		 */
		public BoxObject(int id, int height, int width) {
			super();
			this.id = id;
			this.height = height;
			this.width = width;
		}

		/**
		 * @return the id
		 */
		public int getId() {
			return id;
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
			return "BoxObject [id=" + id + ", height=" + height + ", width=" + width + "]";
		}
    	
    }



    
}

