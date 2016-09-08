package com.ryanjbaxter.spring.cloud.ocr.races;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ryanjbaxter on 7/21/16.
 */
public class ParticipantsClientFallback implements ParticipantsClient{
    @Override
    public List<Participant> getParticipantsFeignClient(String raceId) {
        return new ArrayList<Participant>();
    }

    @Override
    public List<Participant> getAllParticipantsFeignClient() {
        return new ArrayList<Participant>();
    }
}
