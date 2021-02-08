package com.rest.dto;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@XmlRootElement
public class Participants {
	@Id
	@GeneratedValue
	private int participantId;
	private String name;
	private String collegeId;
	private String branch;
	private String phone;
	private String email;
	private String password;
	private String imageName;
	public Participants() {
		
	}
	
	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "ParticipantEvents",
            joinColumns = {@JoinColumn(name = "participantId")},
            inverseJoinColumns = {@JoinColumn(name = "eventId")}
    )
	@JsonIgnoreProperties(value = {"participants"}, allowSetters=true)
    private Set<EventDetails> eventDetails = new HashSet<>();
	
	public int getParticipantId() {
		return participantId;
	}

	public void setParticipantId(int participantId) {
		this.participantId = participantId;
	}

	public String getCollegeId() {
		return collegeId;
	}

	public void setCollegeId(String collegeId) {
		this.collegeId = collegeId;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public Set<EventDetails> getEventDetails() {
		return eventDetails;
	}

	public void setEventDetails(Set<EventDetails> eventDetails) {
		this.eventDetails = eventDetails;
	}

	
	
}
