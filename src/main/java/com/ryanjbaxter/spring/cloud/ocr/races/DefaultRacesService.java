package com.ryanjbaxter.spring.cloud.ocr.races;

import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ryan Baxter
 */
public class DefaultRacesService implements RacesService {

	private List<Race> races = new ArrayList<>();
	private ParticipantsService participantsService;

	public DefaultRacesService(ParticipantsService participantsService) {
		this.participantsService = participantsService;
		races.add(new Race("Spartan Beast", "123", "MA", "Boston"));
		races.add(new Race("Tough Mudder RI", "456", "RI", "Providence"));
	}

	@Override
	public Flux<Race> getAllRaces() {
		return Flux.fromIterable(races);
	}

	@Override
	public Flux<RaceWithParticipants> getAllRacesWithParticipants() {
		List<RaceWithParticipants> returnRaces = new ArrayList<RaceWithParticipants>();
		Flux<Participant> participants = participantsService.getAllParticipants();

		for(Race r : races) {
			returnRaces.add(new RaceWithParticipants(r, participants.filter(p -> p.getRaces().contains(r.getId()))));
		}
		return Flux.fromIterable(returnRaces);
	}
}
