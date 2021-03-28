package com.pujanov;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.pujanov.config.SplashScreenLoader;
import com.pujanov.util.CheckApp;
import com.sun.javafx.application.LauncherImpl;

import javafx.application.Application;
import lombok.extern.slf4j.Slf4j;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.spring.SpringFxWeaver;

@Slf4j
@ConfigurationPropertiesScan("com.pujanov.config.properties")
@SpringBootApplication
public class GoogleSearchClientApplication {

	public static void main(String[] args) {
		try {
			if(CheckApp.isRunning()) {
				Application.launch(GoogleSearchErrorApplication.class, args);
				System.exit(0);
			} else {
				LauncherImpl.launchApplication(GoogleSearchFxApplication.class, SplashScreenLoader.class, args);
			}
		} catch(Throwable th) {
			log.error("Error occured {}", th.getLocalizedMessage());
		}
	}
	
	@Bean
	RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate;
	}
	
	@Bean
	public FxWeaver fxWeaver(ConfigurableApplicationContext context) {
		return new SpringFxWeaver(context);
	}
}
