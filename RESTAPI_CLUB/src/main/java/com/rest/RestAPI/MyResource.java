package com.rest.RestAPI;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.dto.Credits;
import com.rest.dto.EventDetails;
import com.rest.dto.FileUpload;
import com.rest.dto.MemberDetails;
import com.rest.dto.Participants;
import com.rest.dto.Quiz;
import com.ts.dao.CreditsDAO;
import com.ts.dao.EventDetailsDAO;
import com.ts.dao.FileUploadDAO;
import com.ts.dao.MemberDAO;
import com.ts.dao.ParticipantDAO;
import com.ts.dao.QuizDAO;

@Path("myresource")
public class MyResource {
	@Path("hi")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String hi() throws UnsupportedEncodingException {
		System.out.println("Hi...");
		return "Hi Service!";
	}
	
    
    @Path("getAllParticipants")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Participants> getParticipants() {

		ParticipantDAO participantDao = new ParticipantDAO();
		List <Participants> ParticipantsList = participantDao.getAllParticipants();

		return ParticipantsList;
	}
    @Path("getAllMembers")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<MemberDetails> getMembers() {

		MemberDAO memberDao = new MemberDAO();
		List <MemberDetails> MembersList = memberDao.getAllMembers();

		return MembersList;
	}
    @Path("getAllEvents")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<EventDetails> getEvents() {

    	EventDetailsDAO eventDetailsDao = new EventDetailsDAO();
		List <EventDetails> eventsList = eventDetailsDao.getAllEvents();
		return eventsList;
	}
    @Path("getAllEventsForPat/{participantId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<EventDetails> getAllEventsForPat(@PathParam("participantId") int participantId) {
    	System.out.println("Recieved path params in pat: "+participantId); 
    	EventDetailsDAO eventDetailsDao = new EventDetailsDAO();
		List <EventDetails> eventsList = eventDetailsDao.getAllEventsForPat(participantId);
		return eventsList;
	}
    
    
    
    
    
    
    
    @Path("regParticipant")
    @POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public int regParticipant(@FormDataParam("imageName") InputStream fileInputStream,@FormDataParam("imageName") FormDataContentDisposition
	formDataContentDisposition, @FormDataParam("name") String name, @FormDataParam("collegeId") String collegeId , @FormDataParam("branch") String branch , @FormDataParam("phone") String phone , @FormDataParam("email") String email , @FormDataParam("password") String password) throws IOException { 
		int read = 0;
		byte[] bytes = new byte[1024];
		
		String path = this.getClass().getClassLoader().getResource("").getPath();
		String pathArr[] = path.split("/WEB-INF/classes/");
		
		FileOutputStream out = new FileOutputStream(new File(pathArr[0]+"/image/", formDataContentDisposition.getFileName()));
				
				
		while((read = fileInputStream.read(bytes)) != -1){	
			
			out.write(bytes,0,read);
		}
		out.flush();
		out.close();
		
		Participants participant = new Participants();
		participant.setName(name);
		participant.setCollegeId(collegeId);
		participant.setBranch(branch);
		participant.setPhone(phone);
		participant.setEmail(email);
		participant.setPassword(password);
		participant.setImageName(formDataContentDisposition.getFileName());
		ParticipantDAO participantDao = new ParticipantDAO();
		return participantDao.register(participant);
	}
    @Path("updatePatWithImg")
    @POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public int updatePatWithImg(@FormDataParam("imageName") InputStream fileInputStream,@FormDataParam("imageName") FormDataContentDisposition
	formDataContentDisposition,@FormDataParam("participantId") int participantId, @FormDataParam("name") String name, @FormDataParam("collegeId") String collegeId , @FormDataParam("branch") String branch , @FormDataParam("phone") String phone , @FormDataParam("email") String email , @FormDataParam("password") String password, @FormDataParam("eventDetails") String eventDetails) throws IOException { 
		int read = 0;
		byte[] bytes = new byte[1024];
		
		String path = this.getClass().getClassLoader().getResource("").getPath();
		String pathArr[] = path.split("/WEB-INF/classes/");
		
		FileOutputStream out = new FileOutputStream(new File(pathArr[0]+"/image/", formDataContentDisposition.getFileName()));
				
				
		while((read = fileInputStream.read(bytes)) != -1){	
			
			out.write(bytes,0,read);
		}
		out.flush();
		out.close();
		System.out.println(eventDetails+"hii");
		Participants participant = new Participants();
		participant.setParticipantId(participantId);
		participant.setName(name);
		participant.setCollegeId(collegeId);
		participant.setBranch(branch);
		participant.setPhone(phone);
		participant.setEmail(email);
		participant.setPassword(password);
		participant.setImageName(formDataContentDisposition.getFileName());
		ParticipantDAO participantDao = new ParticipantDAO();
		Set<EventDetails> events=participantDao.getAllParticipantEvents(email, password);
		participant.setEventDetails(events);
		return participantDao.update(participant);
	}
    @Path("updateMemWithImg")
    @POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public int updateMemWithImg(@FormDataParam("imageName") InputStream fileInputStream,@FormDataParam("imageName") FormDataContentDisposition
	formDataContentDisposition,@FormDataParam("memberId") int memberId, @FormDataParam("name") String name, @FormDataParam("collegeId") String collegeId , @FormDataParam("branch") String branch , @FormDataParam("phone") String phone , @FormDataParam("email") String email , @FormDataParam("password") String password,  @FormDataParam("role") String role , @FormDataParam("category") String category ) throws IOException { 
		int read = 0;
		byte[] bytes = new byte[1024];
		
		String path = this.getClass().getClassLoader().getResource("").getPath();
		String pathArr[] = path.split("/WEB-INF/classes/");
		
		FileOutputStream out = new FileOutputStream(new File(pathArr[0]+"/image/", formDataContentDisposition.getFileName()));
				
				
		while((read = fileInputStream.read(bytes)) != -1){	
			
			out.write(bytes,0,read);
		}
		out.flush();
		out.close();
		
		MemberDetails memberDetails = new MemberDetails();
		memberDetails.setMemberId(memberId);
		memberDetails.setName(name);
		memberDetails.setCollegeId(collegeId);
		memberDetails.setBranch(branch);
		memberDetails.setPhone(phone);
		memberDetails.setEmail(email);
		memberDetails.setPassword(password);
		memberDetails.setRole(role);
		memberDetails.setCategory(category);
		memberDetails.setImageName(formDataContentDisposition.getFileName());
		MemberDAO memberDao = new MemberDAO();
		Set<EventDetails> events=memberDao.getMemEvents(email);
		memberDetails.setEventDetails(events);
		return memberDao.update(memberDetails);
	}
    @Path("regMember")
    @POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public int regMember(@FormDataParam("imageName") InputStream fileInputStream,@FormDataParam("imageName") FormDataContentDisposition
	formDataContentDisposition, @FormDataParam("name") String name, @FormDataParam("collegeId") String collegeId , @FormDataParam("branch") String branch , @FormDataParam("phone") String phone , @FormDataParam("email") String email , @FormDataParam("password") String password,  @FormDataParam("role") String role , @FormDataParam("category") String category ) throws IOException { 
		int read = 0;
		byte[] bytes = new byte[1024];
		
		String path = this.getClass().getClassLoader().getResource("").getPath();
		String pathArr[] = path.split("/WEB-INF/classes/");
		
		FileOutputStream out = new FileOutputStream(new File(pathArr[0]+"/image/", formDataContentDisposition.getFileName()));
				
				
		while((read = fileInputStream.read(bytes)) != -1){	
			
			out.write(bytes,0,read);
		}
		out.flush();
		out.close();
		
		MemberDetails memberDetails = new MemberDetails();
		memberDetails.setName(name);
		memberDetails.setCollegeId(collegeId);
		memberDetails.setBranch(branch);
		memberDetails.setPhone(phone);
		memberDetails.setEmail(email);
		memberDetails.setPassword(password);
		memberDetails.setRole(role);
		memberDetails.setCategory(category);
		memberDetails.setImageName(formDataContentDisposition.getFileName());
		MemberDAO memberDao = new MemberDAO();
		return memberDao.register(memberDetails);
	}
    @Path("regEvent")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public int regEvent(EventDetails eventDetails) {
		System.out.println("Data Recieved in event update : " + eventDetails);
		/*for(EventDetails eventDetails : participant.getEventDetails()){
			System.out.println(eventDetails.getEventName()); 
		}*/
		EventDetailsDAO eventDetilsDao = new EventDetailsDAO();
		int p=eventDetilsDao.register(eventDetails);
		return p;
	}
    
    
    @Path("updateParticipant")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public int updateParticipant(Participants participant) {
		System.out.println("Data Recieved in Participant update : " + participant);
		ParticipantDAO participantDao = new ParticipantDAO();
		int p=participantDao.update(participant);
		return p;
	}
    @Path("updateMember")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public int updateMember(MemberDetails member) {
		System.out.println("Data Recieved in member update : " + member);
		MemberDAO memeberDao = new MemberDAO();
		int p=memeberDao.update(member);
		return p;
	}
    @Path("registerQuiz")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public int registerQuiz(Quiz quiz) {
		System.out.println("Data Recieved in quiz register : " + quiz);
		QuizDAO quizDao = new QuizDAO();
		int p=quizDao.register(quiz);
		return p;
	}
    
    
    
    
    
    
    
    @Path("getPatByUserPass/{email}/{password}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Participants getPatByUserPass(@PathParam("email") String email,@PathParam("password") String password) {
		System.out.println("Recieved path params in pat: "+email+" "+password); 
		ParticipantDAO participantDao = new ParticipantDAO();
		Participants participants = participantDao.getPatByUserPass(email, password);
		return participants;
	}
    
    @Path("getMemByUserPass/{email}/{password}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public MemberDetails getMemByUserPass(@PathParam("email") String email,@PathParam("password") String password) {
		System.out.println("Recieved path params in mem: "+email+" "+password); 
		MemberDAO memberDao = new MemberDAO();
		MemberDetails member = memberDao.getMemByUserPass(email, password);
		return member;
	}
    @Path("getMemByUserEmail/{email}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public MemberDetails getMemByUserEmail(@PathParam("email") String email) {
		System.out.println("Recieved path params in mem: "+email+" "); 
		MemberDAO memberDao = new MemberDAO();
		MemberDetails member = memberDao.getMemByUserEmail(email);
		return member;
	}
    @Path("getAllMemberEvents/{email}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<EventDetails> getAllMemberEvents(@PathParam("email") String email) {
		System.out.println("Recieved path params in mem: "+email+" "); 
		MemberDAO memberDao = new MemberDAO();
		List<EventDetails> events = memberDao.getAllMemberEvents(email);
		return events;
	}
    
    
    
    
    @Path("participantEvents/{email}/{password}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<EventDetails> participantEvents(@PathParam("email") String email,@PathParam("password") String password) {
		System.out.println("Recieved path params in patEvents: "+email+" "+password); 
		ParticipantDAO participantDao = new ParticipantDAO();
		List<EventDetails> events = participantDao.getParticipantEvents(email, password);
		return events;
	}
    
    
    
    
    
    @Path("mailForResult")
    @POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
   public String mailForResult(@FormDataParam("eventName") String eventName,@FormDataParam("marks") String marks,@FormDataParam("total") String total,@FormDataParam("email") String e) throws MessagingException {
       DateFormat simple = new SimpleDateFormat("dd MMM yyyy HH:mm:ss:SSS Z"); 
       //Date result = new Date(startDate);
       String subject="You have successfuly submitted the event: "+eventName;
       String body="You have scored "+marks+" points out of "+total+" points in the event "+eventName+" \nTime: "+simple.format(new Date());
       String email=e;
       String host = "smtp.gmail.com";
       String from = "divyakantamani6031@gmail.com";
       String pass = "divya@2018";
       System.out.println(email+"Received in mail");
       Properties props = System.getProperties();
    
       props.put("mail.smtp.starttls.enable", "true"); // added this line
       props.put("mail.smtp.host", host);
       props.put("mail.smtp.user", from);
       props.put("mail.smtp.password", pass);
       props.put("mail.smtp.port", "587");
       props.put("mail.smtp.auth", "true");
    
       String[] to = {email}; // added this line
    
       Session session = Session.getDefaultInstance(props, null);
       MimeMessage message = new MimeMessage(session);
       message.setFrom(new InternetAddress(from));

       InternetAddress[] toAddress = new InternetAddress[to.length];

       // To get the array of addresses

       for( int i=0; i < to.length; i++ ) {
	       toAddress[i] = new InternetAddress(to[i]);
       }
   
       for( int i=0; i < toAddress.length; i++) {
           // changed from a while loop
           message.addRecipient(Message.RecipientType.TO, toAddress[i]);
       }
   
       message.setSubject(subject);
       message.setText(body);

       Transport transport = session.getTransport("smtp");

       transport.connect(host, from, pass);
       transport.sendMessage(message, message.getAllRecipients());

       transport.close();

       return "Successful";
   }
    
    @Path("mail")
    @POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
   public String mail(@FormDataParam("eventName") String eventName,@FormDataParam("startDate") long startDate,@FormDataParam("endDate") long endDate,@FormDataParam("email") String e) throws MessagingException {
       DateFormat simple = new SimpleDateFormat("dd MMM yyyy HH:mm:ss:SSS Z"); 
       //Date result = new Date(startDate);
       String subject="You have registered for event: "+eventName;
       String body="The event "+eventName+" will start on "+simple.format(startDate)+" and ends on "+simple.format(endDate)+"\n"+"Participate without fail";
       String email=e;
       String host = "smtp.gmail.com";
       String from = "divyakantamani6031@gmail.com";
       String pass = "divya@2018";
       System.out.println(email+"Received in mail");
       Properties props = System.getProperties();
    
       props.put("mail.smtp.starttls.enable", "true"); // added this line
       props.put("mail.smtp.host", host);
       props.put("mail.smtp.user", from);
       props.put("mail.smtp.password", pass);
       props.put("mail.smtp.port", "587");
       props.put("mail.smtp.auth", "true");
    
       String[] to = {email}; // added this line
    
       Session session = Session.getDefaultInstance(props, null);
       MimeMessage message = new MimeMessage(session);
       message.setFrom(new InternetAddress(from));

       InternetAddress[] toAddress = new InternetAddress[to.length];

       // To get the array of addresses

       for( int i=0; i < to.length; i++ ) {
	       toAddress[i] = new InternetAddress(to[i]);
       }
   
       for( int i=0; i < toAddress.length; i++) {
           // changed from a while loop
           message.addRecipient(Message.RecipientType.TO, toAddress[i]);
       }
   
       message.setSubject(subject);
       message.setText(body);

       Transport transport = session.getTransport("smtp");

       transport.connect(host, from, pass);
       transport.sendMessage(message, message.getAllRecipients());

       transport.close();

       return "Successful";
   }
    
    
    
    @Path("getAllQues/{quizName}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Quiz> getAllQues(@PathParam("quizName") String quizName) {
		System.out.println("Recieved path params in getallques: "+quizName+" "); 
		QuizDAO quizDao = new QuizDAO();
		List<Quiz> quesSet = quizDao.getAllQues(quizName);
		return quesSet;
	}
    
    
    @Path("storeCredits")
    @POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public int storeCredits(@FormDataParam("eventId") int eventId,@FormDataParam("participantId") int participantId,@FormDataParam("marks") int marks) throws IOException {
    	Credits credit = new Credits();
    	credit.setEventId(eventId);
    	credit.setParticipantId(participantId);
    	credit.setMarks(marks);
    	CreditsDAO creditsDao = new CreditsDAO();
    	return creditsDao.register(credit);
    	
    }
    
    @Path("isParticipate/{eventId}/{participantId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public int isParticipate(@PathParam("eventId") int eventId,@PathParam("participantId") int participantId) {
		System.out.println("Recieved path params in participate: "+eventId+" "+participantId); 
		CreditsDAO creditsDao = new CreditsDAO();
		int p = creditsDao.getCredit(eventId, participantId);
		return p;
	}
    
    @Path("postAssignment")
    @POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public int postAssignment(@FormDataParam("imageName") InputStream fileInputStream,@FormDataParam("imageName") FormDataContentDisposition
	formDataContentDisposition, @FormDataParam("participantId") int participantId , @FormDataParam("name") String name,@FormDataParam("eventId") int eventId) throws IOException { 
		int read = 0;
		byte[] bytes = new byte[1024];
		System.out.println("hiiiiiiiiiiiiii");
		String path = this.getClass().getClassLoader().getResource("").getPath();
		String pathArr[] = path.split("/WEB-INF/classes/");
		
		FileOutputStream out = new FileOutputStream(new File(pathArr[0]+"/image/", formDataContentDisposition.getFileName()));
				
				
		while((read = fileInputStream.read(bytes)) != -1){	
			
			out.write(bytes,0,read);
		}
		out.flush();
		out.close();
		
		FileUpload fileUpload = new FileUpload();
		fileUpload.setEventId(eventId);
		fileUpload.setName(name);
		fileUpload.setParticipantId(participantId);
		fileUpload.setImageName(formDataContentDisposition.getFileName());
		Credits credit = new Credits();
		credit.setEventId(eventId);
		credit.setMarks(0);
		credit.setParticipantId(participantId);
		FileUploadDAO fileUploadDAO = new FileUploadDAO();
		return fileUploadDAO.register(fileUpload,credit);
	}
    
    @Path("getAllUploads/{eventId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<FileUpload> getAllUploads(@PathParam("eventId") int eventId) {
		System.out.println("Recieved path params in getalluploads: "+eventId+" "); 
		FileUploadDAO fileUploadDao = new FileUploadDAO();
		List<FileUpload> uploadsSet = fileUploadDao.getAllUploads(eventId);
		return uploadsSet;
	}
    
    
    @Path("giveScore")
    @POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public int giveScore( @FormDataParam("uploadId") int uploadId, @FormDataParam("eventId") int eventId , @FormDataParam("participantId") int participantId , @FormDataParam("name") String name , @FormDataParam("imageName") String imageName , @FormDataParam("credit") int credit) throws IOException { 
    	System.out.println("Recieved path in giveScore: "+uploadId+" "); 
		FileUpload upload = new FileUpload();
		upload.setEventId(eventId);
		upload.setImageName(imageName);
		upload.setName(name);
		upload.setParticipantId(participantId);
		upload.setUploadId(uploadId);
		FileUploadDAO fileUploadDao = new FileUploadDAO(); 
		return fileUploadDao.delete(upload,credit);
	}
    
    @Path("getEventIds/{participantId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<EventDetails> getEventIds(@PathParam("participantId") int participantId) {

		ParticipantDAO participantDao = new ParticipantDAO();
		List <EventDetails> events = participantDao.getAllEventIds(participantId);

		return events;
	}
    
    
}
