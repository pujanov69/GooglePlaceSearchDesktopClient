package com.pujanov.service;

import java.util.List;

import com.pujanov.dto.Business;

public interface RestService {
		
	List<Business> searchPlacesApiCall(String requestParams);
	
}
