package com.ryanjbaxter.spring.cloud.ocr.races;

import java.util.ArrayList;
import java.util.List;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Ryan Baxter
 */
@FeignClient(name="participants", fallback = ParticipantsClientFallback.class)
public interface FeignParticipantsClient {
	//When using Brixton it is important that this method name be different than
	//the method name of the HystrixCommand that is wrapping it.  Prior to 9.0.1
	//OpenFeign always used the method name when creating Hystrix Command keys
	//so since both method names were the same Hystrix thought both circuits were
	//the same.  This will be fixed in Camden.
	@RequestMapping(method = RequestMethod.GET, value="/races/{raceId}")
	List<Participant> getParticipantsFeignClient(@PathVariable("raceId") String raceId);

	@RequestMapping(method = RequestMethod.GET, value="/")
	List<Participant> getAllParticipantsFeignClient();
}
class ParticipantsClientFallback implements FeignParticipantsClient{
	@Override
	public List<Participant> getParticipantsFeignClient(String raceId) {
		return new ArrayList<Participant>();
	}

	@Override
	public List<Participant> getAllParticipantsFeignClient() {
		return new ArrayList<Participant>();
	}
}
