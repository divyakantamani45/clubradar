package com.ts.db;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.rest.dto.MemberDetails;
import com.rest.dto.Credits;
import com.rest.dto.EventDetails;
import com.rest.dto.FileUpload;
import com.rest.dto.Participants;

public class HibernateTemplate {

	private static SessionFactory sessionFactory;
	
	static {
		sessionFactory=new Configuration().configure().buildSessionFactory();
	}
	
	public static int addObject(Object obj)
	{
		System.out.println("Inside Template...");
		Session session=null;
		int result=0;
		
		Transaction tx=null;
		
		try {
			session=sessionFactory.openSession();
			
			tx=session.beginTransaction();
			
			session.save(obj);
			
			tx.commit();
			result=1;
			session.close();
			
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}
		return result;
	}
	
	public static Object getObject(Class c,Serializable serializable)
	{
		Object obj=null;
		
		try {			
			return sessionFactory.openSession().get(c,serializable);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return obj;
	}
	public static Object getParticipantByUserPass(String email,String password) {
	
	 String queryString = "from Participants where email = :email and password =:password";
	  Session session = sessionFactory.openSession();
	  Query query = session.createQuery(queryString);
	  query.setString("email", email);
	  query.setString("password", password);
	  Object queryResult = query.uniqueResult();
	  //Participants participant = (Participants)queryResult;
	  //session.close();
	  return queryResult; 
	}
	public static Object check(String email,String password) {
		
		 String queryString = "from Participants where email = :email and password =:password";
		  Session session = sessionFactory.openSession();
		  Query query = session.createQuery(queryString);
		  query.setString("email", email);
		  query.setString("password", password);
		  Object queryResult = query.uniqueResult();
		  //Participants participant = (Participants)queryResult;
		  session.close();
		  return queryResult; 
		}
	public static int getCredit(int eventId,int participantId) {
		
		 String queryString = "from Credits where eventId = :eventId and participantId =:participantId";
		  Session session = sessionFactory.openSession();
		  Query query = session.createQuery(queryString);
		  query.setLong("eventId", eventId);
		  query.setLong("participantId", participantId);
		  Object queryResult = query.uniqueResult();
		  if(queryResult == null)
			  return 0;
		  else
			  return 1;
		  //Participants participant = (Participants)queryResult;
		  //session.close();
		  //return queryResult; 
    }
	public static Object getMemByUserPass(String email,String password) {
		
		String queryString = "from MemberDetails where email = :email and password =:password";
		  Query query = sessionFactory.openSession().createQuery(queryString);
		  query.setString("email", email);
		  query.setString("password", password);
		  Object queryResult = query.uniqueResult();
		  
		  //Participants participant = (Participants)queryResult;
		  return queryResult; 
	}
	public static Object getObjectByEmail(String email) {
		
		String queryString = "from Participants where email = :email";
		  Query query = sessionFactory.openSession().createQuery(queryString);
		  query.setString("email", email);
		  Object queryResult = query.uniqueResult();
		  Participants participant = (Participants)queryResult;
		  return participant; 
	}
    public static Object getMemByEmail(String email) {
		
		 String queryString = "from MemberDetails where email = :email";
		  Query query = sessionFactory.openSession().createQuery(queryString);
		  query.setString("email", email);
		  Object queryResult = query.uniqueResult();
		  MemberDetails member = (MemberDetails)queryResult;
		  return member; 
	}
    public static Object getMemCheck(String email) {
		
		 String queryString = "from MemberDetails where email = :email";
		 Session session = sessionFactory.openSession();
		  Query query = session.createQuery(queryString);
		  query.setString("email", email);
		  Object queryResult = query.uniqueResult();
		  MemberDetails member = (MemberDetails)queryResult;
		  session.close();
		  return member; 
	}
    public static Participants getParticipant(int participantId) {
		System.out.println("in get pat");
		 String queryString = "from Participants where participantId = :participantId";
		 Session session = sessionFactory.openSession();
		  Query query = session.createQuery(queryString);
		  query.setLong("participantId", participantId);
		  Object queryResult = query.uniqueResult();
		  Participants participants = (Participants)queryResult;
		  session.close();
		  return participants; 
	}
    public static int updateCredits(int eventId,int participantId, int marks) {
    	
    	Session session = sessionFactory.openSession();
		
		 String queryString = "update Credits set marks = :marks where eventId = :eventId and participantId = :participantId";
		  Query query = session.createQuery(queryString);
		  query.setLong("marks", marks);
		  query.setLong("eventId", eventId);
		  query.setLong("participantId", participantId);
		  int p = query.executeUpdate();
		  session.close();
		  return p; 
		  
	}
	
	public static List<Object> getObjectListByQuery(String query)
	{
		return sessionFactory.openSession().createQuery(query).list();
	}
	public static List<Object> getQuestionsListByName(String qName)
	{
		String queryString = "from Quiz where quizName = :qName";
		  Query query = sessionFactory.openSession().createQuery(queryString);
		  query.setString("qName", qName);
		  return query.list();
	}
	public static List<Object> getUploadsListById(int eventId)
	{
		String queryString = "from FileUpload where eventId = :eventId";
		  Query query = sessionFactory.openSession().createQuery(queryString);
		  query.setLong("eventId", eventId);
		  return query.list();
	}
	public static int updateObject(Object obj)
	{
		int result=0;
		
		Transaction tx=null;
		
		try {
			
			Session session=sessionFactory.openSession();
			tx=session.beginTransaction();
			
			session.saveOrUpdate(obj);
			
			tx.commit();
			
			result=1;
			session.close();
			
		} catch (Exception e) {
		
			tx.rollback();
			
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static int deleteObject(FileUpload upload)
	{
		int result=0;
		
		Session session=sessionFactory.openSession();
		
		Transaction tx=session.beginTransaction();
		
		try {
			
			//Object obj=session.get(c,serializable);
			
			session.delete(upload);
			
			tx.commit();
			
			result=1;
						
		} catch (Exception e) {
			
			e.printStackTrace();
			
			tx.rollback();
		}
		finally {
			session.close();
		}
		return result;
	}

	public static List<Object> getObjectListByName(Class c, String columName, String value) {
		Session session=sessionFactory.openSession();
		  Criteria criteria = session.createCriteria(c);
			Criterion nameCriterion = Restrictions.eq(columName, value);
			criteria.add(nameCriterion);
			return criteria.list();
	}
	
	public static List<Credits> getEventIdsByQuery(int participantId)
	{
		  System.out.println("in getIds");
		  Session session=sessionFactory.openSession();
		  String queryString = "from Credits where participantId = :participantId";
		  Query query = session.createQuery(queryString);
		  query.setLong("participantId", participantId);
		  List<Credits> eventIds = query.list();
		  session.close();
		  return eventIds;
	}
}
