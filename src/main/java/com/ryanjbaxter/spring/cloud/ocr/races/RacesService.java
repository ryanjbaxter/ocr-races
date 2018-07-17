package com.ryanjbaxter.spring.cloud.ocr.races;

import reactor.core.publisher.Flux;

import java.util.List;

/**
 * @author Ryan Baxter
 */
public interface RacesService {

	public Flux<Race> getAllRaces();

	public Flux<RaceWithParticipants> getAllRacesWithParticipants();
}

class Race {
	private String name;
	private String id;
	private String state;
	private String city;

	public Race(String name, String id, String state, String city) {
		super();
		this.name = name;
		this.id = id;
		this.state = state;
		this.city = city;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Race race = (Race) o;

		if (!getName().equals(race.getName())) return false;
		if (!getId().equals(race.getId())) return false;
		if (!getState().equals(race.getState())) return false;
		return getCity().equals(race.getCity());
	}

	@Override
	public int hashCode() {
		int result = getName().hashCode();
		result = 31 * result + getId().hashCode();
		result = 31 * result + getState().hashCode();
		result = 31 * result + getCity().hashCode();
		return result;
	}
}

class RaceWithParticipants extends Race {
	private Flux<Participant> participants;

	public RaceWithParticipants(Race r, List<Participant> participants) {
		super(r.getName(), r.getId(), r.getState(), r.getCity());
		this.participants = Flux.fromIterable(participants);
	}

	public RaceWithParticipants(Race r, Flux<Participant> participants) {
		super(r.getName(), r.getId(), r.getState(), r.getCity());
		this.participants = participants;
	}

	public Flux<Participant> getParticipants() {
		return participants;
	}

	public void setParticipants(List<Participant> participants) {
		this.participants = Flux.fromIterable(participants);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;

		RaceWithParticipants that = (RaceWithParticipants) o;

		return getParticipants().collectList().block().equals(that.getParticipants().collectList().block());
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + getParticipants().hashCode();
		return result;
	}
}
