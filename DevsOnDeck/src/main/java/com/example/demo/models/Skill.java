package com.example.demo.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="skills")
public class Skill {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message="Skill needs a name")
	private String name;
	
	@Column(nullable = false, columnDefinition = "TINYINT(1)")
	private boolean language;
	
	@Column(nullable = false, columnDefinition = "TINYINT(1)")
	private boolean framework;
	
	@Column(nullable = false, columnDefinition = "TINYINT(1)")
	private boolean devtool;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "developer_skills", joinColumns = @JoinColumn(name = "skill_id"), 
	inverseJoinColumns = @JoinColumn(name = "developer_id"))
	private List<Skill> skilleddevs;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "position_skills", joinColumns = @JoinColumn(name = "skill_id"),
	inverseJoinColumns = @JoinColumn(name = "position_id"))
	private List<Skill> skillsposition;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isLanguage() {
		return language;
	}

	public void setLanguage(boolean language) {
		this.language = language;
	}

	public boolean isFramework() {
		return framework;
	}

	public void setFramework(boolean framework) {
		this.framework = framework;
	}

	public boolean isDevtool() {
		return devtool;
	}

	public void setDevtool(boolean devtool) {
		this.devtool = devtool;
	}

	public List<Skill> getSkilleddevs() {
		return skilleddevs;
	}

	public void setSkilleddevs(List<Skill> skilleddevs) {
		this.skilleddevs = skilleddevs;
	}

	public List<Skill> getSkillsposition() {
		return skillsposition;
	}

	public void setSkillsposition(List<Skill> skillsposition) {
		this.skillsposition = skillsposition;
	}

	
	
	
}
