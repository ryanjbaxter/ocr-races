package com.ryanjbaxter.spring.cloud.ocr.races;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ryan Baxter
 */
@RestController
public class RacesController {

	private RacesService racesService;

	public RacesController(RacesService racesService) {
		this.racesService = racesService;
	}

	@RequestMapping("/")
	public List<Race> getRaces() {
		return racesService.getAllRaces();
	}

	@RequestMapping("/participants")
	public List<RaceWithParticipants> getRacesWithParticipants() {
		return racesService.getAllRacesWithParticipants();
	}

//	@RequestMapping("/ribbon-participants")
//	public List<RaceWithParticipants> getRacesWithParticipantsUsingRibbon() {
//		List<RaceWithParticipants> returnRaces = new ArrayList<RaceWithParticipants>();
//		List<Participant> participants = participantsBean.getParticipantsWithRibbon();
//		for(Race r : races) {
//			returnRaces.add(new RaceWithParticipants(r, participants.stream().filter(
//					participant -> participant.getRaces().contains(r.getId())).collect(Collectors.toList())));
//		}
//		return returnRaces;
//	}
}
