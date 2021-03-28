/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pujanov.dto;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;


public class Business {    
    private  final SimpleStringProperty businessName;
    private final SimpleStringProperty businessAddress;
    private final SimpleStringProperty city;
    private final SimpleStringProperty state;
    private final SimpleStringProperty zipCode;
	
    
    public Business(String businessName, String businessAddress, String city,
			String state, String zipCode) {
		this.businessName = new SimpleStringProperty(businessName);
		this.businessAddress = new SimpleStringProperty(businessAddress);
		this.city = new SimpleStringProperty(city);
		this.state = new SimpleStringProperty(state);
		this.zipCode = new SimpleStringProperty(zipCode);
	}


	public String getBusinessName() {
		return businessName.get();
	}


	public String getBusinessAddress() {
		return businessAddress.get();
	}


	public String getCity() {
		return city.get();
	}


	public String getState() {
		return state.get();
	}


	public String getZipCode() {
		return zipCode.get();
	}


	public void setBusinessName(String businessName) {
		this.businessName.set(businessName);
	}


	public void setBusinessAddress(String businessAddress) {
		this.businessAddress.set(businessAddress);
	}


	public void setCity(String city) {
		this.city.set(city);
	}


	public void setState(String state) {
		this.state.set(state);
	}


	public void setZipCode(String zipCode) {
		this.zipCode.set(zipCode);
	}
    
    
    
}
    
