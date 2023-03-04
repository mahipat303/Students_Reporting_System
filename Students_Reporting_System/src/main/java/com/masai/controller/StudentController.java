package com.masai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.masai.dto.AvgOfMarks;
import com.masai.dto.StudentMarks;
import com.masai.model.Student;
import com.masai.service.StudentService;

@RestController
public class StudentController {

	@Autowired
	private StudentService sService;

	@PostMapping("/student")
	public ResponseEntity<Student> registerStudentHandler(@RequestBody Student student) {

		Student stu = sService.registerStudent(student);

		return new ResponseEntity<Student>(stu, HttpStatus.CREATED);

	}

	@PostMapping("/student/marks")
	public ResponseEntity<String> addMarksHandler(@RequestBody StudentMarks marks) {

		String message = sService.addMarks(marks);

		return new ResponseEntity<String>(message, HttpStatus.CREATED);
	}

	@GetMapping("/student/avg/{sem}")
	public ResponseEntity<AvgOfMarks> averageMarksHandler(@PathVariable(name = "sem") int sem) {

		AvgOfMarks avgMarks = sService.averageMarks(sem);

		return new ResponseEntity<AvgOfMarks>(avgMarks, HttpStatus.OK);
	}

	@GetMapping("/student/toptwo/{sem}")
	public ResponseEntity<List<Student>> topTwoHandler(@PathVariable(name = "sem") int sem) {

		return new ResponseEntity<>(sService.topTwo(sem), HttpStatus.OK);
	}

	@GetMapping("/student/avgPercentage/{sem}")
	public ResponseEntity<Double> averagePercentageHandler(@PathVariable(name = "sem") int sem) {

		return new ResponseEntity<>(sService.averagePercentage(sem), HttpStatus.OK);
	}

}
