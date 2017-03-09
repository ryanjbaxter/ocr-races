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
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureStubRunner
@DirtiesContext
@ActiveProfiles("test")
public class FeignOcrRacesApplicationTests extends OcrRacesApplicationTestsBase {

	@Test
	public void serviceTypeTest() {
		assertTrue(FeignParticipantsService.class.isInstance(participantsService));
	}
}
