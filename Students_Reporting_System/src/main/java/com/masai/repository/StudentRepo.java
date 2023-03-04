package com.masai.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.model.Student;

public interface StudentRepo extends JpaRepository<Student,Integer> {
	
	
	public Optional<Student> findByEmail(String email);

}
