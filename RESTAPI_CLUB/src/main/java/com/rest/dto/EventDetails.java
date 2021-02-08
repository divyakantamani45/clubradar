package com.rest.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rest.dto.MemberDetails;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class EventDetails {
	@Id
	@GeneratedValue
	private int eventId;
	@Column(columnDefinition = "TEXT")
	private String eventName;
	private Date startDate;
	private Date endDate;
	@Lob
	private String description;
	private String eventType;
	private String mode;
	@Column(columnDefinition = "TEXT")
	private String place;
	private int points;
	private int obtPoints;
	
	
	@ManyToMany(mappedBy="eventDetails",fetch = FetchType.EAGER)
	@JsonIgnoreProperties(value = {"eventDetails"}, allowSetters = true)
	private Set<Participants> participants = new HashSet<>();
	
	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "MemberEvents",
            joinColumns = {@JoinColumn(name = "eventId")},
            inverseJoinColumns = {@JoinColumn(name = "memberId")}
    )
	@JsonIgnoreProperties(value = {"eventDetails"}, allowSetters = true)
    private Set<MemberDetails> memberDetails = new HashSet<>();
	
	public EventDetails() {
		
	}
	public int getEventId() {
		return eventId;
	}
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	
	public int getObtPoints() {
		return obtPoints;
	}
	public void setObtPoints(int obtPoints) {
		this.obtPoints = obtPoints;
	}
	public Set<MemberDetails> getMemberDetails() {
		return memberDetails;
	}
	public void setMemberDetails(Set<MemberDetails> memberDetails) {
		this.memberDetails = memberDetails;
	}
	
	public Set<Participants> getParticipants() {
		return participants;
	}
	public void setParticipants(Set<Participants> participants) {
		this.participants = participants;
	}
	
}
