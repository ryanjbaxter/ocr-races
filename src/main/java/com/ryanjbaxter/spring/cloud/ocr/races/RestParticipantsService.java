package com.ryanjbaxter.spring.cloud.ocr.races;

import java.util.Arrays;
import java.util.List;
import org.springframework.web.client.RestTemplate;

/**
 * @author Ryan Baxter
 */
public class RestParticipantsService implements ParticipantsService {

	private RestTemplate rest;

	public RestParticipantsService(RestTemplate rest) {
		this.rest = rest;
	}

	@Override
	public List<Participant> getAllParticipants() {
		return Arrays.asList(rest.getForObject("http://participants/", Participant[].class));
	}

	@Override
	public List<Participant> getParticipants(String raceId) {
		return Arrays.asList(rest.getForObject("http://participants/races/" + raceId, Participant[].class));
	}
}
