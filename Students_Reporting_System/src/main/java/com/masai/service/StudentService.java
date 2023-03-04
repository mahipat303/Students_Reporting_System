package com.masai.service;

import java.util.List;

import com.masai.dto.AvgOfMarks;
import com.masai.dto.StudentMarks;
import com.masai.exception.StudentException;
import com.masai.model.Student;

public interface StudentService {

	Student registerStudent(Student student) throws StudentException;

	String addMarks(StudentMarks marks) throws StudentException;

	double averagePercentage(int sem) throws StudentException;

	AvgOfMarks averageMarks(int sem) throws StudentException;

	List<Student> topTwo(int sem) throws StudentException;

}
