package com.ts.dao;

import java.util.List;

import org.hibernate.SessionFactory;

import com.rest.dto.Credits;
import com.rest.dto.EventDetails;
import com.rest.dto.FileUpload;
import com.rest.dto.Quiz;
import com.ts.db.HibernateTemplate;

public class FileUploadDAO {
private SessionFactory factory = null;
	
	public int register(FileUpload fileUpload,Credits credit) {
		System.out.println(fileUpload); 
		int p= HibernateTemplate.addObject(fileUpload);
		int q=HibernateTemplate.addObject(credit);
		int r=p&q;
		return r;
	}
	public static List<FileUpload> getAllUploads(int eventId) {
		List<FileUpload> uploads=(List)HibernateTemplate.getUploadsListById(eventId);
		System.out.println("Inside All questions ..."+uploads);
		return uploads;
	}
	public int delete(FileUpload fileUpload,int credit) {
		System.out.println(fileUpload); 
		int q=HibernateTemplate.updateCredits(fileUpload.getEventId(),fileUpload.getParticipantId(),credit);
		int p= HibernateTemplate.deleteObject(fileUpload);
		int r=p&q;
		return r;
	}
	
}
