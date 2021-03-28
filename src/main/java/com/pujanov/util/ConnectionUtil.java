package com.pujanov.util;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

import org.springframework.stereotype.Component;

import com.pujanov.config.properties.RestApiProperties;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class ConnectionUtil {
	private final RestApiProperties apiProperties;
	public boolean checkInternetConnection() {
		try {
			try (Socket socket = new Socket()) {
				socket.connect(new InetSocketAddress("www.google.com", 80), apiProperties.getTimeoutInMillisecond());
			}
			log.info("Internet connection Fine :)");
			return true;
		} catch(IOException exception) {
			System.out.println("Internet Connection Problem");
			log.info("Internet connection Prob!");
			return false;
		}
	}
}
