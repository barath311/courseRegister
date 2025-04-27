package org.example.repository;

import org.example.model.CourseRegistry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseReg extends JpaRepository<CourseRegistry,Integer> {
void deleteByName(String name);
boolean existsByName(String name);
}