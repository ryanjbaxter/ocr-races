package com.ryanjbaxter.spring.cloud.ocr.races;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient 
@EnableFeignClients
@EnableCircuitBreaker
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
	public ParticipantsService restParticipantsService(RestTemplate rest) {
		return new RestParticipantsService(rest);
	}

	@Bean
	public RacesService racesService(ParticipantsService participantsService) {
		return new DefaultRacesService(participantsService);
	}

	@Bean
	@LoadBalanced
	public RestTemplate restTemple() {
		return new RestTemplateBuilder().build();
	}

    public static void main(String[] args) {
        SpringApplication.run(OcrRacesApplication.class, args);
    }
}

@Component
class Sampler {
	@Bean
	public AlwaysSampler defaultSampler() {
		return new AlwaysSampler();
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