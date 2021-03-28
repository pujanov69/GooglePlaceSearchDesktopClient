package com.pujanov.service.impl;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pujanov.config.properties.RestApiProperties;
import com.pujanov.dto.Business;
import com.pujanov.service.RestService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RestServiceImpl implements RestService {
	
	
	private final RestApiProperties restApiProperties;
	private final RestTemplate restTemplate;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	public RestServiceImpl(RestTemplate restTemplate, RestApiProperties restApiProperties) {
		this.restTemplate = restTemplate;
		this.restApiProperties = restApiProperties;
	}


	@Override
	public List<Business> searchPlacesApiCall(String requestParams) {
		// TODO Auto-generated method stub
		RestTemplate restTemplate = new RestTemplate();
		
		final String baseUrl = restApiProperties.getUrl() + "query=" + requestParams + "&key=" + restApiProperties.getApiKey();
	    URI uri=null;
		try {
			uri = new URI(baseUrl);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     
		
		RequestEntity<?> requestEntity = RequestEntity
				.method(HttpMethod.POST, uri)
				//.contentType(MediaType.APPLICATION_JSON)
				.build();
		
		ParameterizedTypeReference<String> responseType = new ParameterizedTypeReference<String>() {
		};
	 
		ResponseEntity responseEntity = restTemplate.exchange(requestEntity, responseType);
	
	    
	    System.out.println("Body-->" + responseEntity.getBody().toString());
	
	    List<Business> businesses = new ArrayList<Business>();
	    
	    try {
			JSONObject jsonObject = new JSONObject(responseEntity.getBody().toString());
			JSONArray resultsArray = new JSONArray();
			resultsArray = jsonObject.getJSONArray("results");
		
			
			for(int i=0; i < resultsArray.length(); i++) {
				try {
				JSONObject resultObject = resultsArray.getJSONObject(i);
				System.out.println("Formatted Address--->"+ resultObject.getString("formatted_address"));
				String bName = resultObject.getString("name");
				String bAddress = resultObject.getString("formatted_address");
				
				String[] strngArr = bAddress.split(",");
				
				String bCity = strngArr[1];
				
				String bState = strngArr[2].split(" ")[1];
				
				String bZipCode =  strngArr[2].split(" ")[2];
				
				System.out.println("#city->" + bCity  + "#state-->" +  bState + "#zipcode-->" + bZipCode + "#businessName->" + bName);
				
				Business business = new Business(bName, bAddress, bCity, bState, bZipCode);
				businesses.add(business);
				}catch(Exception e) {
					
				}
			}
			
	    } catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    return businesses;
	    
	}

		

}
