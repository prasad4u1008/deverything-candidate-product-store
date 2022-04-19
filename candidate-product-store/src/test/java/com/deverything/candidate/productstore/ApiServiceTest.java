package com.deverything.candidate.productstore;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.deverything.candidate.productstore.impl.ApiServiceImpl;
import com.deverything.candidate.productstore.pojo.CheckoutObject;
import com.deverything.candidate.productstore.pojo.ProductObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ApiServiceTest {

	@Autowired
	ApiService api;

	private int testProductID3 = 3;
	private int testProductID7 = 7;
			
	
	@Test
	public void testAllTheThings() {
		System.out.println("Let's get all products from the API:");
		System.out.println("RESULT: " + api.getProducts());
		System.out.println("---------------------------------------------------------------------------------------");

		System.out.println("Let's list all products with a price higher then 300");
		System.out.println("RESULT: " + api.getProducts().stream().filter(p -> ((ProductObject) p).getPrice() > 300)
				.collect(Collectors.toList()));
		System.out.println("---------------------------------------------------------------------------------------");

		System.out.println("Let's get product dimensions for products with id 3 and 7");
		System.out.println("RESULT: Dimensions for Product Id 3 : - " + api.getProductDimensions(testProductID3));
		System.out.println("RESULT: Dimensions for Product Id 7 : - " + api.getProductDimensions(testProductID7));
		System.out.println("---------------------------------------------------------------------------------------");

		System.out.println("Get all boxes and choose the best one that fits both the products 3 and 7 in a single box");
		System.out.println("RESULT: All Availble Boxes" + api.getBoxes());
		System.out.println("RESULT: Best box fits the products with Id 3,7 " + ((ApiServiceImpl) api).checkFitment(Arrays.asList(testProductID3, testProductID7)));
		System.out.println("---------------------------------------------------------------------------------------");

		System.out.println("Now we place the order using the checkout using the API");
		CheckoutObject checkOutObject = new CheckoutObject(((ApiServiceImpl) api).checkFitment(Arrays.asList(3, 7)).getId(),
				Arrays.asList(testProductID3,testProductID7));
		System.out.println("RESULT: Checkout Summary : " + api.checkout(checkOutObject));
		System.out.println("---------------------------------------------------------------------------------------");
	}
}