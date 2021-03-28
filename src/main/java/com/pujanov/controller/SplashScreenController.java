package com.pujanov.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXProgressBar;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

public class SplashScreenController implements Initializable {
	@FXML
	private Label loadingLabel;
	
	@FXML
	private JFXProgressBar progressBar;
	
	public static Label label;
	public static ProgressBar statProgressBar;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		label = loadingLabel;
		statProgressBar = progressBar;
	}
	
}
