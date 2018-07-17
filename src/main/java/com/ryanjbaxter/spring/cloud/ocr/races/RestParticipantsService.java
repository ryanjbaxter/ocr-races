package com.ryanjbaxter.spring.cloud.ocr.races;

import reactor.core.publisher.Flux;

import java.util.List;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author Ryan Baxter
 */
public class RestParticipantsService implements ParticipantsService {

	private WebClient.Builder builder;

	public RestParticipantsService(WebClient.Builder builder) {
		this.builder = builder;
	}

	@Override
	public Flux<Participant> getAllParticipants() {
		return builder.baseUrl("http://participants").build().get().retrieve().bodyToFlux(Participant.class);
	}

	@Override
	public Flux<Participant> getParticipants(String raceId) {
		return builder.baseUrl("http://participants").build().get().retrieve().bodyToFlux(Participant.class);
	}
}
