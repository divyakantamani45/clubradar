package com.rest.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Quiz {
	@Id
	@GeneratedValue
	private int quizId;
	@Column(columnDefinition = "TEXT")
	private String quizName;
	@Lob
	private String questionDes;
	private String optiona_1;
	private String optionb_2;
	private String optionc_3;
	private String optiond_4;
	private String correctAns;
	public int getQuizId() {
		return quizId;
	}
	public void setQuizId(int quizId) {
		this.quizId = quizId;
	}
	public String getQuizName() {
		return quizName;
	}
	public void setQuizName(String quizName) {
		this.quizName = quizName;
	}
	public String getQuestionDes() {
		return questionDes;
	}
	public void setQuestionDes(String questionDes) {
		this.questionDes = questionDes;
	}
	public String getOptiona_1() {
		return optiona_1;
	}
	public void setOptiona_1(String optiona_1) {
		this.optiona_1 = optiona_1;
	}
	public String getOptionb_2() {
		return optionb_2;
	}
	public void setOptionb_2(String optionb_2) {
		this.optionb_2 = optionb_2;
	}
	public String getOptionc_3() {
		return optionc_3;
	}
	public void setOptionc_3(String optionc_3) {
		this.optionc_3 = optionc_3;
	}
	public String getOptiond_4() {
		return optiond_4;
	}
	public void setOptiond_4(String optiond_4) {
		this.optiond_4 = optiond_4;
	}
	public String getCorrectAns() {
		return correctAns;
	}
	public void setCorrectAns(String correctAns) {
		this.correctAns = correctAns;
	}
	

}
