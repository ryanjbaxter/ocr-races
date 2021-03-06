package com.ryanjbaxter.spring.cloud.ocr.races;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import com.keyholesoftware.troublemaker.client.EnableTroubleMaker;

@SpringBootApplication
@EnableFeignClients
@EnableCircuitBreaker
@EnableTroubleMaker
public class OcrRacesApplication {

	@Bean
	public ParticipantsClientFallback ParticipantsClientFallback() {
		return new ParticipantsClientFallback();
	}

	@Bean
	@ConditionalOnProperty(name="races.rest.enabled", havingValue = "false", matchIfMissing = true)
	public ParticipantsService feignParticipantsService() {
		return new FeignParticipantsService();
	}

	@Bean
	@ConditionalOnProperty(name="races.rest.enabled", havingValue = "true", matchIfMissing = false)
	public ParticipantsService restParticipantsService(WebClient.Builder builder) {
		return new RestParticipantsService(builder);
	}

	@Bean
	public RacesService racesService(ParticipantsService participantsService) {
		return new DefaultRacesService(participantsService);
	}

	@Bean
	@LoadBalanced
	public WebClient.Builder webClientBuilder() {
		WebClient.Builder builder = WebClient.builder();
		return builder;
	}

    public static void main(String[] args) {
        SpringApplication.run(OcrRacesApplication.class, args);
    }
}

@RestController
class MyHealth implements HealthIndicator {

	private boolean healthy = true;

	@RequestMapping("/healthy")
	public boolean healthy() {
		this.healthy = true;
		return healthy;
	}

	@RequestMapping("/sick")
	public boolean sick() {
		this.healthy = false;
		return healthy;
	}

	@Override
	public Health health() {
		if(healthy) {
			return Health.up().build();
		} else {
			return Health.down().build();
		}
	}
}