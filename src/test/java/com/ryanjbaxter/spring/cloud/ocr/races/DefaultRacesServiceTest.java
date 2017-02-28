package com.ryanjbaxter.spring.cloud.ocr.races;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;

/**
 * @author Ryan Baxter
 */
@RunWith(MockitoJUnitRunner.class)
public class DefaultRacesServiceTest {

	@Mock
	private ParticipantsService participantsService;
	private List<Race> races;
	private List<RaceWithParticipants> raceWithParticipants;
	private List<Participant> participants;

	@Before
	public void setup() {
		this.races = new ArrayList<>();
		this.raceWithParticipants = new ArrayList<>();
		this.participants = new ArrayList<>();
		Race beast = new Race("Spartan Beast", "123", "MA", "Boston");
		Race mudder = new Race("Tough Mudder RI", "456", "RI", "Providence");
		Participant ryan = new Participant("Ryan", "Baxter", "MA", "S", Arrays.asList(beast.getId(), mudder.getId()));
		Participant stephanie = new Participant("Stephanie", "Baxter", "MA", "S", Arrays.asList(mudder.getId()));
		this.races.add(beast);
		this.races.add(mudder);
		this.participants.add(ryan);
		this.participants.add(stephanie);
		this.raceWithParticipants.add(new RaceWithParticipants(beast, Arrays.asList(ryan)));
		this.raceWithParticipants.add(new RaceWithParticipants(mudder, Arrays.asList(ryan, stephanie)));
	}

	@After
	public void tearDown() {
		this.races = new ArrayList<>();
		this.participants = new ArrayList<>();
		this.raceWithParticipants = new ArrayList<>();
	}


	@Test
	public void getAllRacesTest() {
		DefaultRacesService racesService = new DefaultRacesService(participantsService);
		assertEquals(races, racesService.getAllRaces());
	}

	@Test
	public void getAllRacesWithParticipantsTest() {
		doReturn(participants).when(participantsService).getAllParticipants();
		DefaultRacesService racesService = new DefaultRacesService(participantsService);
		assertEquals(raceWithParticipants, racesService.getAllRacesWithParticipants());
	}
}
