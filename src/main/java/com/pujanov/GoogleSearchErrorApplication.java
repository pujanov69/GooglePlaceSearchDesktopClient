package com.pujanov;

import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class GoogleSearchErrorApplication extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setHeaderText(null);
		alert.setContentText("Application already running!");
		alert.show();
	}

}
