package com.example.demo.models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name="developers")
public class Developer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "first name must be entered")
	private String fname;
	
	@NotEmpty(message = "last name must be entered")
	private String lname;
	
	@NotEmpty(message="Email must not be empty")
	@Email
	private String email;
	
	@NotEmpty(message = "Address can not be empty")
	private String address;
	
	@NotEmpty(message = "City must be entered")
	private String city;
	
	@NotEmpty(message = "Please select a state")
	private String state;
	
	@NotEmpty(message = "password name must be entered")
	@Size(min = 8,  message = "password must be between at least 8 characters long")
	private String password;
	
	@Transient
	private String passConfirm;
	
	private String biography;
	
	@Column(updatable = false)
	private Date createdAt;
	private Date updatedAt;
	
	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}
	
	
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "developer_skills", joinColumns = @JoinColumn(name = "developer_id"), 
	inverseJoinColumns = @JoinColumn(name = "skill_id"))
	private List<Skill> myskills;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "applications", joinColumns = @JoinColumn(name = "developer_id"), inverseJoinColumns = @JoinColumn(name = "position_id"))
	private List<Position> applications;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassConfirm() {
		return passConfirm;
	}

	public void setPassConfirm(String passConfirm) {
		this.passConfirm = passConfirm;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}



	public List<Position> getApplications() {
		return applications;
	}

	public void setApplications(List<Position> applications) {
		this.applications = applications;
	}

	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}

	public List<Skill> getMyskills() {
		return myskills;
	}

	public void setMyskills(List<Skill> myskills) {
		this.myskills = myskills;
	}
	
	
}
