package com.ts.dao;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.hibernate.SessionFactory;

import com.rest.dto.EventDetails;
import com.rest.dto.MemberDetails;
import com.rest.dto.Participants;
import com.ts.db.HibernateTemplate;

public class MemberDAO {
    private SessionFactory factory = null;
	
	public int register(MemberDetails member) {
		System.out.println(member); 
		return HibernateTemplate.addObject(member);
	}
	public static List<MemberDetails> getAllMembers() {
		List<MemberDetails> members=(List)HibernateTemplate.getObjectListByQuery("From MemberDetails");
		System.out.println("Inside All MemberDetails ..."+members);
		return members;
	}
	public int update(MemberDetails member) {
		System.out.println("in dao" +member); 
		return HibernateTemplate.updateObject(member);
	}
    public MemberDetails getMemByUserPass(String email, String password) {
		
		MemberDetails member = (MemberDetails)HibernateTemplate.getMemByUserPass(email,password);
		System.out.println("In DAO"+member);
		return member;
	}
    public MemberDetails getMemByUserEmail(String email) {
		
		MemberDetails member = (MemberDetails)HibernateTemplate.getMemByEmail(email);
		System.out.println("In DAO"+member);
		return member;
	}
   public Set<EventDetails> getMemEvents(String email) {
		
		MemberDetails member = (MemberDetails)HibernateTemplate.getMemCheck(email);
		System.out.println("In DAO"+member);
		return member.getEventDetails();
	}
    public List<EventDetails> getAllMemberEvents(String email) {
    	
		List<EventDetails> events = new ArrayList<>();
		MemberDetails member = (MemberDetails)HibernateTemplate.getMemByEmail(email);
		//LocalDate d;
		//Date end;
		//ZoneId defaultZoneId = ZoneId.systemDefault();
		for(EventDetails event:member.getEventDetails()) {
			//d=event.getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			//d=d.plusDays(10);
			//end=Date.from(d.atStartOfDay(defaultZoneId).toInstant());
			if((!(event.getEventType().equals("quiz"))) && event.getStartDate().compareTo(new Date()) <= 0 && event.getEndDate().compareTo(new Date())>=0) {
				events.add(event);
			}
			else {
				System.out.println(event.getEventType()+" in else ");
			}
		}
		System.out.println("In DAO"+events);
		return events;
	}

}
