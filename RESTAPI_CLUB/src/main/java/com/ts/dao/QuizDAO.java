package com.ts.dao;

import java.util.List;

import org.hibernate.SessionFactory;

import com.rest.dto.Participants;
import com.rest.dto.Quiz;
import com.ts.db.HibernateTemplate;

public class QuizDAO {
	
	private SessionFactory factory = null;
	
	public int register(Quiz quiz) {
		System.out.println(quiz); 
		return HibernateTemplate.addObject(quiz);
	}
	
	public static List<Quiz> getAllQues(String qName) {
		List<Quiz> questions=(List)HibernateTemplate.getQuestionsListByName(qName);
		System.out.println("Inside All questions ..."+questions);
		return questions;
	}

}
