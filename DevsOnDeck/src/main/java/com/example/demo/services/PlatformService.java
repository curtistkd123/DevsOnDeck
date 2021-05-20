package com.example.demo.services;

import java.util.List;

import javax.validation.Valid;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Developer;
import com.example.demo.models.Organization;
import com.example.demo.models.Position;
import com.example.demo.models.Skill;
import com.example.demo.repositories.DeveloperRepository;
import com.example.demo.repositories.OrganizationRepository;
import com.example.demo.repositories.PositionRepository;
import com.example.demo.repositories.SkillRepository;

@Service
public class PlatformService {
	
	@Autowired
	DeveloperRepository dRepo;
	
	@Autowired
	OrganizationRepository oRepo;
	
	@Autowired
	PositionRepository pRepo;
	
	@Autowired
	SkillRepository sRepo;

	public Developer createDev(Developer dev) {
		// TODO Auto-generated method stub
		String hashed = BCrypt.hashpw(dev.getPassword(), BCrypt.gensalt());
		dev.setPassword(hashed);
		return dRepo.save(dev);
	}

	
	public boolean authenticateDev(String email, String password) {
		Developer dev = dRepo.findByEmail(email);
		if(dev == null) {
			return false;
		}else {
			if(BCrypt.checkpw(password, dev.getPassword())) {
				return true;
			}else {
				return false;
			}
		}
	}
	
	public void createOrg(@Valid Organization org) {
		// TODO Auto-generated method stub
		String hashed = BCrypt.hashpw(org.getPassword(), BCrypt.gensalt());
		org.setPassword(hashed);
		oRepo.save(org);
	}
	
	public boolean authenticateOrg(String email, String password) {
		Organization org = oRepo.findByEmail(email);
		if(org == null) {
			return false;
		}else {
			if(BCrypt.checkpw(password, org.getPassword())) {
				return true;
			}else {
				return false;
			}
		}
	}


	public Organization findOrgByEmail(String email) {
		// TODO Auto-generated method stub
		return oRepo.findByEmail(email);
		
	}


	public Organization findOrgById(Long id) {
		// TODO Auto-generated method stub
		return oRepo.findById(id).orElse(null);
	}


	public List<Skill> findAllSkills() {
		// TODO Auto-generated method stub
		return (List<Skill>) sRepo.findAll();
	}


	public @Valid Position savePosition(@Valid Position position) {
		// TODO Auto-generated method stub
		return pRepo.save(position);
	}


	public Skill findSkill(Long id) {
		// TODO Auto-generated method stub
		return sRepo.findById(id).orElse(null);
	}


	public List<Skill> findLanguages() {
		// TODO Auto-generated method stub
		return sRepo.findAllByLanguageContaining(true);
	}


	public Developer saveDev(Developer dev) {
		// TODO Auto-generated method stub
		return dRepo.save(dev);
	}


	public Developer findDeveloper(Long id) {
		// TODO Auto-generated method stub
		return dRepo.findById(id).orElse(null);
	}


	public @Valid Developer findDeveloperByEmail(String email) {
		// TODO Auto-generated method stub
		return dRepo.findByEmail(email);
	}


	public List<Position> findAllPositions() {
		// TODO Auto-generated method stub
		return (List<Position>) pRepo.findAll();
	}


	public List<Organization> findAllOrgs() {
		// TODO Auto-generated method stub
		return (List<Organization>) oRepo.findAll();
	}


	

}
