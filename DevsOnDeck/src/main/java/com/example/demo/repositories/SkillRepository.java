package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.models.Skill;

public interface SkillRepository extends CrudRepository<Skill, Long> {

	List<Skill> findAllByLanguageContaining(boolean b);

	List<Skill> findAllByFrameworkContaining(boolean b);

	

}
