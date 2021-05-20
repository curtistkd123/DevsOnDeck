package com.example.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Position;

@Repository
public interface PositionRepository extends CrudRepository<Position, Long> {

}
