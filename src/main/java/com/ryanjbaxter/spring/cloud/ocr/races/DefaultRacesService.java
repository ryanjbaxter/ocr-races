package com.ryanjbaxter.spring.cloud.ocr.races;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
	public List<Race> getAllRaces() {
		return races;
	}

	@Override
	public List<RaceWithParticipants> getAllRacesWithParticipants() {
		List<RaceWithParticipants> returnRaces = new ArrayList<RaceWithParticipants>();
		List<Participant> participants = participantsService.getAllParticipants();
		for(Race r : races) {
			returnRaces.add(new RaceWithParticipants(r, participants.stream().filter(
					participant -> participant.getRaces().contains(r.getId())).collect(Collectors.toList())));
		}
		return returnRaces;
	}
}
