package com.example.application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.application.entities.Structure;

@Repository
public interface StructureRepository extends JpaRepository<Structure, Long> {
}