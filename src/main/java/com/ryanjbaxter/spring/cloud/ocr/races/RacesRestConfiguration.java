package com.ryanjbaxter.spring.cloud.ocr.races;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Ryan Baxter
 */
@Component
@ConfigurationProperties(prefix="races.rest")
public class RacesRestConfiguration {

	private boolean enabled;

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}
