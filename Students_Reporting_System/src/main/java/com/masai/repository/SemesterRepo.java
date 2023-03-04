package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.model.Semester;

public interface SemesterRepo extends JpaRepository<Semester, Integer> {
	
	

}
