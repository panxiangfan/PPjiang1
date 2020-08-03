package com.yr.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Student {
	
	private Integer id;

	private String name;
	private String email;
	private Integer sex;
	
	@JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date time;
	
	private Float money;
	
	private String head;
	
	private School school;

	private  int schID;

	public int getSchID() {
		return schID;
	}

	public void setSchID(int schID) {
		this.schID = schID;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Float getMoney() {
		return money;
	}

	public void setMoney(Float money) {
		this.money = money;
	}

	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}



	@Override
	public String toString() {
		return "Student{" +
				"id=" + id +
				", name='" + name + '\'' +
				", email='" + email + '\'' +
				", sex=" + sex +
				", time=" + time +
				", money=" + money +
				", head='" + head + '\'' +
				", school=" + school +
				'}';
	}
}
