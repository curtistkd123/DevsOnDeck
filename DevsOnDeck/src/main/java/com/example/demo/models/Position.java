package com.example.demo.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name="positions")
public class Position {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message="title is needed")
	@Size(min =5,  message = "Title must be at least 5 characters long")
	private String title;
	
	@NotEmpty(message="Job Description is needed")
	private String description;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "position_skills", joinColumns = @JoinColumn(name = "position_id"),
	inverseJoinColumns = @JoinColumn(name = "skill_id"))
	private List<Skill> posSkills;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organization_id")
	private Organization organization;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "applications", joinColumns = @JoinColumn(name = "position_id"), inverseJoinColumns = @JoinColumn(name = "developer_id"))
	private List<Developer> applicants;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Skill> getPosSkills() {
		return posSkills;
	}

	public void setPosSkills(List<Skill> posSkills) {
		this.posSkills = posSkills;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public List<Developer> getApplicants() {
		return applicants;
	}

	public void setApplicants(List<Developer> applicants) {
		this.applicants = applicants;
	}
	
	
}
