package com.ryanjbaxter.spring.cloud.ocr.races;

import reactor.core.publisher.Flux;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Ryan Baxter
 */
public class FeignParticipantsService implements ParticipantsService {

	private FeignParticipantsClient client;

	@Autowired
	public void setParticipantsClient(FeignParticipantsClient client) {
		this.client = client;
	}

	@Override
	public Flux<Participant> getAllParticipants() {
		return Flux.fromIterable(client.getAllParticipantsFeignClient());
	}

	@Override
	public Flux<Participant> getParticipants(String raceId) {
		return Flux.fromIterable(client.getParticipantsFeignClient(raceId));
	}
}
