package com.rest.dto;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@XmlRootElement

public class MemberDetails {
	@Id
	@GeneratedValue
	private int memberId;
	private String name;
	private String collegeId;
	private String branch;
	private String phone;
	private String email;
	private String password;
	private String role;
	private String category;
	private String imageName;
	
	@ManyToMany(mappedBy="memberDetails",fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = {"memberDetails"}, allowSetters = true)
	private Set<EventDetails> eventDetails = new HashSet<>();
	
	public MemberDetails() {
	}
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public String getCollegeId() {
		return collegeId;
	}
	public void setCollegeId(String collegeId) {
		this.collegeId = collegeId;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	
	public Set<EventDetails> getEventDetails() {
		return eventDetails;
	}
	public void setEventDetails(Set<EventDetails> eventDetails) {
		this.eventDetails = eventDetails;
	}
	
		
}
