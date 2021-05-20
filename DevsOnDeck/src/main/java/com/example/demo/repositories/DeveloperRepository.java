package com.example.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Developer;

@Repository
public interface DeveloperRepository extends CrudRepository<Developer, Long>{

	Developer findByEmail(String email);

}
