package com.ryanjbaxter.spring.cloud.ocr.races;

import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;


public class OcrRacesApplicationTestsBase {

	@Autowired
	protected ParticipantsService participantsService;

	private List<Participant> participants = new ArrayList<>();


	//Hack to work around https://github.com/spring-cloud/spring-cloud-commons/issues/156
	static {
		System.setProperty("eureka.client.enabled", "false");
		System.setProperty("spring.cloud.config.failFast", "false");
	}

	@Before
	public void setup() {
		this.participants = new ArrayList<>();
		this.participants.add(new Participant("Ryan", "Baxter", "MA", "S", Arrays.asList("123", "456")));
		this.participants.add(new Participant("Stephanie", "Baxter", "MA", "S", Arrays.asList("456")));
	}

	@After
	public void tearDown() {
		this.participants = new ArrayList<>();
	}

	@Test
	public void contextLoads() {
		Flux<Participant> participantList = participantsService.getAllParticipants();
		assertEquals(participants, participantList.collectList().block());
	}

}
