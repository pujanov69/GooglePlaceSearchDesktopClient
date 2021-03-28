package com.pujanov.config;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.pujanov.controller.MainViewController;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import net.rgielen.fxweaver.core.FxWeaver;

@Component
public class PrimaryStageInitializer implements ApplicationListener<StageReadyEvent> {
	public final FxWeaver fxWeaver;
	
	public PrimaryStageInitializer(FxWeaver fxWeaver) {
		this.fxWeaver = fxWeaver;
	}

	@Override
	public void onApplicationEvent(StageReadyEvent event) {
		Stage stage = event.stage;
		//stage.initStyle(StageStyle.UNDECORATED);
		//stage.isResizable();
		stage.getIcons().add(new Image("images/app_icon.jpg"));
		Scene scene = new Scene(fxWeaver.loadView(MainViewController.class));
		stage.setScene(scene);
		stage.show();
	}

}
