package com.ryanjbaxter.spring.cloud.ocr.races;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

/**
 * @author Ryan Baxter
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = {"races.rest.enabled=true"})
@AutoConfigureStubRunner(ids = {"com.ryanjbaxter.spring.cloud:ocr-participants:+:stubs"}, workOffline = true)
@DirtiesContext
@ActiveProfiles("test")
public class RestOcrRacesApplicationTests extends OcrRacesApplicationTestsBase {
	@Test
	public void serviceTypeTest() {
		assertTrue(RestParticipantsService.class.isInstance(participantsService));
	}
}
