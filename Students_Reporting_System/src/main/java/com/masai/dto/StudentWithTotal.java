package com.masai.dto;

import java.util.Comparator;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudentWithTotal implements Comparable<StudentWithTotal> {

	private Integer marks;
	private String email;

	@Override
	public int compareTo(StudentWithTotal that) {
		if (this.getMarks() > that.getMarks())
			return -1;
		if (this.getMarks() < that.getMarks())
			return 1;
		// TODO Auto-generated method stub
		return 0;
	}

}
