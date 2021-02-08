package com.ts.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.hibernate.SessionFactory;

import com.rest.dto.EventDetails;
import com.rest.dto.MemberDetails;
import com.rest.dto.Participants;
import com.ts.db.HibernateTemplate;

public class EventDetailsDAO {
	
	private SessionFactory factory = null;
	
	public int register(EventDetails eventDetails) {
		System.out.println(eventDetails); 
		return HibernateTemplate.updateObject(eventDetails);
	}
	
	public static List<EventDetails> getAllEvents() {
		List<EventDetails> events=(List)HibernateTemplate.getObjectListByQuery("From EventDetails");
		System.out.println("Inside All events ..."+events);
		List <EventDetails> copyEvents = new ArrayList<>();
		for(EventDetails event: events) {
			if(event.getEndDate().compareTo(new Date()) >= 0 ) {
				copyEvents.add(event);
			}
		}
		return copyEvents;
	}
	public static List<EventDetails> getAllEventsForPat(int participantId) {
		List<EventDetails> events=(List)HibernateTemplate.getObjectListByQuery("From EventDetails");
		System.out.println("Inside Pat events ..."+events);
		List <EventDetails> copyEvents = new ArrayList<>();
		int flag=0;
		for(EventDetails event: events) {
			flag=0;
			//event.getStartDate().compareTo(new Date()) > 0
			if(event.getStartDate().compareTo(new Date()) > 0) {
				for(Participants pat : event.getParticipants()) {
					  System.out.println(participantId+" from participant");
				      if(pat.getParticipantId()==participantId) {
				    	  flag=1;
				    	  break;
				      }
				}
				if(flag==0)
					copyEvents.add(event);
			}
		}
		return copyEvents;
	}
	
	
}
