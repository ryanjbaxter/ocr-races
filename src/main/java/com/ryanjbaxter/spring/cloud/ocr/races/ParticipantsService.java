package com.ryanjbaxter.spring.cloud.ocr.races;


import java.util.List;

/**
 * @author Ryan Baxter
 */
public interface ParticipantsService {

	public List<Participant> getAllParticipants();

	public List<Participant> getParticipants(String raceId);
}

class Participant {
	private String firstName;
	private String lastName;
	private String homeState;
	private String shirtSize;
	private List<String> races;
	public Participant(String firstName, String lastName, String homeState,
					   String shirtSize, List<String> races) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.homeState = homeState;
		this.shirtSize = shirtSize;
		this.races = races;
	}

	public Participant(){}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Participant that = (Participant) o;

		if (!getFirstName().equals(that.getFirstName())) return false;
		if (!getLastName().equals(that.getLastName())) return false;
		if (!getHomeState().equals(that.getHomeState())) return false;
		if (!getShirtSize().equals(that.getShirtSize())) return false;
		return getRaces().equals(that.getRaces());
	}

	@Override
	public int hashCode() {
		int result = getFirstName().hashCode();
		result = 31 * result + getLastName().hashCode();
		result = 31 * result + getHomeState().hashCode();
		result = 31 * result + getShirtSize().hashCode();
		result = 31 * result + getRaces().hashCode();
		return result;
	}

	public String getHomeState() {
		return homeState;
	}
	public void setHomeState(String homeState) {
		this.homeState = homeState;
	}
	public String getShirtSize() {
		return shirtSize;
	}
	public void setShirtSize(String shirtSize) {
		this.shirtSize = shirtSize;
	}
	public List<String> getRaces() {
		return races;
	}
	public void setRaces(List<String> races) {
		this.races = races;
	}
}