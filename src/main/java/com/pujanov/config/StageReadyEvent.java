package com.pujanov.config;

import org.springframework.context.ApplicationEvent;

import javafx.stage.Stage;

public class StageReadyEvent extends ApplicationEvent {
	private static final long serialVersionUID = -3085720136041287595L;
	public final Stage stage;
	
	public StageReadyEvent(Stage stage) {
		super(stage);
		this.stage = stage;
	}
}
