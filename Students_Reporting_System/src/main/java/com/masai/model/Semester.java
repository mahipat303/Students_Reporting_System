package com.masai.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Entity
@Data
public class Semester {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Min(value = 1)
	@Max(value = 2)
	private Integer sem;

	private Integer english;
	private Integer maths;
	private Integer science;
	public Semester() {
		// TODO Auto-generated constructor stub
	}

	public Semester(@Min(1) @Max(2) Integer sem, Integer english, Integer maths, Integer science) {
		super();
		this.sem = sem;
		this.english = english;
		this.maths = maths;
		this.science = science;
	}

}
//@NamedQuery(
//        name="findAllCustomersWithName",
//        query="SELECT c FROM Customer c WHERE c.name LIKE :custName"
//)
//
//
//The following is an example of the use of a named query: 
//@PersistenceContext
//public EntityManager em;
//...
//customers = em.createNamedQuery("findAllCustomersWithName")
//        .setParameter("custName", "Smith")
//        .getResultList();
