package com.pujanov.config;

import com.pujanov.controller.SplashScreenController;
import com.pujanov.util.ConnectionUtil;

import javafx.application.Preloader;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor
public class SplashScreenLoader extends Preloader {
	private Scene scene;
	private Stage stage;
	
	@FXML
	private Label loadingLabel;

	@Override
	public void init() throws Exception {
		Parent root1 = FXMLLoader.load(getClass().getResource("SplashScreen.fxml")); 
		this.scene = new Scene(root1); 
	}

	@Override
	public void handleStateChangeNotification(StateChangeNotification info) {
		 StateChangeNotification.Type type = info.getType();
	        switch (type) {
	            case BEFORE_START:
	                // Called after GoogleSearchFxApplication#init and before GoogleSearchFxApplication#start is called.
	                log.info("BEFORE_START");
	                stage.hide();
	                break;
	        }
	}

	@Override
	public void handleApplicationNotification(PreloaderNotification info) {
		if(info instanceof ProgressNotification) {
			SplashScreenController.label.setText("Loading...");
			// System.out.println("Value@ :" + ((ProgressNotification) info).getProgress());
			SplashScreenController.statProgressBar.setProgress((((ProgressNotification)info).getProgress()));
		}
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.stage = primaryStage;
		stage.initStyle(StageStyle.UNDECORATED);
		stage.getIcons().add(new Image("images/app_icon.jpg"));
		stage.setScene(this.scene);
		stage.show();
	}
}
