package com.ts.dao;

import org.hibernate.SessionFactory;

import com.rest.dto.Credits;
import com.rest.dto.Participants;
import com.ts.db.HibernateTemplate;

public class CreditsDAO {
private SessionFactory factory = null;
	
	public int register(Credits credit) {
		System.out.println(credit); 
		return HibernateTemplate.addObject(credit);
	}
	public int getCredit(int eventId, int participantId) {
		System.out.println(eventId+" "+participantId);
		int p = (int)HibernateTemplate.getCredit(eventId,participantId);
		//System.out.println("In DAO"+credit.toString());
		return p;
	}
}
