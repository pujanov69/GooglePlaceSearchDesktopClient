/**
 * @author Pujan KC <pujanov69@gmail.com>
 * Since March 28, 2021
 */
package com.pujanov.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "rest-api")
public class RestApiProperties {
	private String apiKey;
	private String url;
	private int timeoutInMillisecond;
	private String exportDirectory;
}
