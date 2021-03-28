package com.pujanov;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.pujanov.config.StageReadyEvent;
import com.sun.javafx.application.LauncherImpl;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.application.Preloader.ProgressNotification;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GoogleSearchFxApplication extends Application {
	private ConfigurableApplicationContext applicationContext;
	private final int COUNT_LIMIT = 10;

	@Override
	public void init() throws Exception {
		this.applicationContext = new SpringApplicationBuilder().sources(GoogleSearchClientApplication.class)
				.run(getParameters().getRaw().toArray(new String[0]));
		for (int i = 1; i <= COUNT_LIMIT; i++) {
			switch (i) {
			case 1:
				log.info("Checking directories....");
				
				break;
			default:
				break;
			}

			double progress = (double) i / COUNT_LIMIT;
			LauncherImpl.notifyPreloader(this, new ProgressNotification(progress));
			Thread.sleep(500);
		}

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		applicationContext.publishEvent(new StageReadyEvent(primaryStage));
	}

	@Override
	public void stop() throws Exception {
		applicationContext.close();
		Platform.exit();
	}

}
