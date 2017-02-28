package com.ryanjbaxter.spring.cloud.ocr.races;

import java.util.List;
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
	public List<Participant> getAllParticipants() {
		return client.getAllParticipantsFeignClient();
	}

	@Override
	public List<Participant> getParticipants(String raceId) {
		return client.getParticipantsFeignClient(raceId);
	}
}
