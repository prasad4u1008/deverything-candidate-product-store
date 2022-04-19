package com.deverything.candidate.productstore.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Component;

import com.deverything.candidate.productstore.ApiService;
import com.deverything.candidate.productstore.pojo.BoxListObject;
import com.deverything.candidate.productstore.pojo.BoxListObject.BoxObject;
import com.deverything.candidate.productstore.pojo.CheckoutObject;
import com.deverything.candidate.productstore.pojo.CheckoutSummaryObject;
import com.deverything.candidate.productstore.pojo.ProductDimensionsObject;
import com.deverything.candidate.productstore.pojo.ProductObject;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Component
public class ApiServiceImpl implements
		ApiService<ProductObject, ProductDimensionsObject, BoxListObject, CheckoutObject, CheckoutSummaryObject> {

	@Override
	public List<ProductObject> getProducts() {
		return Arrays.asList(new Gson().fromJson(
				getResponse(IConstants.GET_PRODUCT_URL, IConstants.REST_CALL_API_GET).get("products"),
				ProductObject[].class));
	}

	@Override
	public ProductDimensionsObject getProductDimensions(int productId) {
		return new Gson().fromJson(
				getResponse(IConstants.GET_PRODUCT_URL + "/" + productId, IConstants.REST_CALL_API_GET),
				ProductDimensionsObject.class);
	}

	@Override
	public BoxListObject getBoxes() {
		return new Gson().fromJson(getResponse(IConstants.GET_BOXES_URL, IConstants.REST_CALL_API_GET),
				BoxListObject.class);
	}

	@Override
	public CheckoutSummaryObject checkout(CheckoutObject checkoutObject) {
		return new Gson().fromJson(
				getResponse(IConstants.POST_CHECKOUT_URL, IConstants.REST_CALL_API_POST, checkoutObject),
				CheckoutSummaryObject.class);
	}

//	@Override
	public BoxObject checkFitment(List<Integer> list) {
		int totalHeight = 0;
		int totalwidth = 0;
		BoxObject boxObj = null;
		for (Integer proId : list) {
			ProductDimensionsObject prdDemenObj = getProductDimensions(proId);
			totalHeight += prdDemenObj.getHeight();
			totalwidth += prdDemenObj.getWidth();
		}
		for (BoxObject box : getBoxes().getBoxes()) {
			if ((box.getHeight() <= totalHeight && box.getWidth() <= totalwidth)
					|| (box.getWidth() <= totalHeight && box.getHeight() <= totalwidth)) {
				boxObj = box;
			}
		}
		return boxObj;
	}

	private JsonObject getResponse(String url, String requestMethod) {
		return getResponse(url, requestMethod, null);
	}

	private JsonObject getResponse(String url, String requestMethod, CheckoutObject checkoutObject) {
		URL getUrl;
		JsonObject rootobj = null;
		try {
			getUrl = new URL(url);
			HttpURLConnection conection = (HttpURLConnection) getUrl.openConnection();

			// Set request method
			conection.setRequestMethod(requestMethod);

			String apiKey = "SUPERSECRETAPIKEY";

			conection.setRequestProperty("USER", "test");
			conection.setRequestProperty("APIKEY", apiKey);
			if (requestMethod.equals(IConstants.REST_CALL_API_POST)) {
				conection.setDoOutput(true);

				try (OutputStream output = conection.getOutputStream()) {
					output.write(new Gson().toJson(checkoutObject).getBytes("UTF-8"));
				}
			}
			// Getting response code
			int responseCode = conection.getResponseCode();

			// If responseCode is 200 means we get data successfully
			if (responseCode == HttpURLConnection.HTTP_OK) {
				JsonParser jp = new JsonParser(); // from gson
				JsonElement root = jp.parse(new InputStreamReader((InputStream) conection.getContent())); // Convert the
				rootobj = root.getAsJsonObject();
			} else {
				switch (responseCode) {
				case 400:
					System.err.println(responseCode + " : Bad Request check the parameters");
					break;
				case 401:
					System.err.println(responseCode + " : Unauthorized check the access");
					break;
				case 403:
					System.err.println(responseCode + " : Forbidden check the API requested");
					break;
				case 404:
					System.err.println(responseCode + " : API Method Not Found");
					break;
				case 405:
					System.err.println(responseCode + " : API Method Not Allowed");
					break;
				case 500:
					System.err.println(responseCode + " : Internal Server Error check the log");
					break;
				case 501:
					System.err.println(responseCode + " : Not Implemented check the log");
					break;
				case 502:
					System.err.println(responseCode + " : Bad Gateway check the log");
					break;
				case 503:
					System.err.println(responseCode + " : Service Unavailable check the log");
					break;
				case 504:
					System.err.println(responseCode + " : Gateway Timeout check the log");
					break;
				default:
					System.err.println(responseCode + " : Unknown Resposne code check the log");
				}

			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return rootobj;
	}

}
