package com.masai.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.dto.AvgOfMarks;
import com.masai.dto.StudentMarks;
import com.masai.dto.StudentWithTotal;
import com.masai.exception.StudentException;
import com.masai.model.Semester;
import com.masai.model.Student;
import com.masai.repository.SemesterRepo;
import com.masai.repository.StudentRepo;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepo sRepo;

	@Autowired
	private SemesterRepo semRepo;

	@Override
	public Student registerStudent(Student student) throws StudentException {

		Optional<Student> stu = sRepo.findByEmail(student.getEmail());

		if (stu.isPresent()) {
			throw new StudentException("student already present...");
		}

		return sRepo.save(student);

	}

	@Override
	public String addMarks(StudentMarks marks) throws StudentException {

		Optional<Student> studentOp = sRepo.findById(marks.getRoll());
		if (studentOp.isEmpty()) {
			throw new StudentException("please enter valid roll number...");
		}

		Student student = studentOp.get();

		List<Semester> sems = student.getSems();
		for (Semester s : sems) {
			if (s.getSem() == marks.getSem()) {
				throw new StudentException("marks is already filled up.....");
			}
		}

		Semester sem = new Semester(marks.getSem(), marks.getEnglish(), marks.getMaths(), marks.getScience());

		student.getSems().add(sem);

		sRepo.save(student);

		return "marks set successfully...";
	}

	@Override
	public double averagePercentage(int sem) throws StudentException {

		List<Semester> semesters = semRepo.findAll();

		if (semesters.isEmpty()) {
			throw new StudentException("no record present...");
		}
		double ans = 0;
		int count = 0;
		for (Semester sem2 : semesters) {
			if (sem2.getSem() == sem) {
				int total = sem2.getEnglish() + sem2.getMaths() + sem2.getScience();
				ans += (total / 3);
				count++;
			}
		}
		if (count==0) {
			throw new StudentException("no record present...");
		}
		return ans / count;
	}

	@Override
	public AvgOfMarks averageMarks(int sem) throws StudentException {

		List<Semester> semesters = semRepo.findAll();

		if (semesters.isEmpty()) {
			throw new StudentException("no record present...");
		}

		int total = 0;
		int eng = 0;
		int sci = 0;
		int math = 0;

		for (Semester sem2 : semesters) {
			if (sem2.getSem() == sem) {
				math += sem2.getMaths();
				sci += sem2.getScience();
				eng += sem2.getEnglish();
				total++;
			}
		}
		return new AvgOfMarks((double) eng / total, (double) math / total, (double) sci / total);
	}

	@Override
	public List<Student> topTwo(int sem) throws StudentException {

		List<Student> students = sRepo.findAll();

		if (students.isEmpty()) {
			throw new StudentException("no record present...");
		}

		List<StudentWithTotal> sts = new ArrayList<>();

		for (Student s : students) {
			List<Semester> sems = s.getSems();

			for (Semester semester : sems) {
				if (semester.getSem() == sem) {

					int total = semester.getMaths() + semester.getEnglish() + semester.getScience();
					sts.add(new StudentWithTotal(total, s.getEmail()));
				}
			}

		}

		Collections.sort(sts);

		List<Student> topTwo = new ArrayList<>();
		int i = 0;
		for (StudentWithTotal s : sts) {
			if (i == 2)
				break;
			topTwo.add(sRepo.findByEmail(s.getEmail()).get());
			i++;
		}

		if (topTwo.isEmpty()) {
			throw new StudentException("no student present in this sem.....");
		}

		return topTwo;
	}

}
