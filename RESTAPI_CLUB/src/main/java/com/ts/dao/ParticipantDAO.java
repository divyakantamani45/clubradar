package com.ts.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.hibernate.SessionFactory;

import com.rest.dto.Credits;
import com.rest.dto.EventDetails;
import com.rest.dto.Participants;
import com.ts.db.HibernateTemplate;

public class ParticipantDAO {
	
	private SessionFactory factory = null;
	
	public int register(Participants participant) {
		System.out.println(participant); 
		return HibernateTemplate.addObject(participant);
	}
	public int update(Participants participant) {
		System.out.println("in dao" +participant); 
		return HibernateTemplate.updateObject(participant);
	}
	
	
	public static List<Participants> getAllParticipants() {
		List<Participants> participants=(List)HibernateTemplate.getObjectListByQuery("From Participants");
		System.out.println("Inside All participants ..."+participants);
		return participants;
	}

	public Participants getPatByUserPass(String email, String password) {
		System.out.println(email+" "+password);
		Participants participant = (Participants)HibernateTemplate.getParticipantByUserPass(email,password);
		System.out.println("In DAO"+participant);
		return participant;
	}
	
	public List<EventDetails> getParticipantEvents(String email, String password) {
		List <EventDetails> copyEvents = new ArrayList<>();
		System.out.println(email+" "+password);
		Participants participant = (Participants)HibernateTemplate.getParticipantByUserPass(email,password);
		System.out.println("In DAO"+participant);
		for(EventDetails event: participant.getEventDetails()) {
			if(event.getEndDate().compareTo(new Date()) >= 0 ) {
				copyEvents.add(event);
			}
		}
		return copyEvents;
	}
	public Set<EventDetails> getAllParticipantEvents(String email, String password) {
		System.out.println(email+" "+password);
		Participants participant = (Participants)HibernateTemplate.check(email,password);
		System.out.println("In DAO"+participant);
		return participant.getEventDetails();
	}
	
	public static List<EventDetails> getAllEventIds(int participantId) {
		List<Credits> eventIds=(List)HibernateTemplate.getEventIdsByQuery(participantId);
		Participants participant=HibernateTemplate.getParticipant(participantId);
		List<EventDetails> events = new ArrayList<>();
		System.out.println("Inside All eventids ..."+eventIds);
		for(EventDetails event : participant.getEventDetails()) {
			for(Credits c : eventIds) {
				if(c.getEventId()==event.getEventId()) {
					event.setObtPoints(c.getMarks());
					events.add(event);
				}
			}
		}
		return events;
	}
	
}
