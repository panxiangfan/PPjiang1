package com.yr.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashSet;
import java.util.Set;

public class School {
	private Integer id;
	private String schoolName;

	private Set<Student> student = new HashSet<Student>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
       @JsonIgnore
	public Set<Student> getStudent() {
		return student;
	}

	public void setStudent(Set<Student> student) {
		this.student = student;
	}

	@Override
	public String toString() {
		return "School [id=" + id + ", schoolName=" + schoolName + "]";
	}

	
}
