package com.yr.dao;

import com.yr.entity.School;

import java.util.List;


public interface SchoolDao {

	/**
	 * 查询
	 * 
	 * @return
	 */
	public List<School> query();


	/**
	 * 查询id
	 * @param id
	 */
	public School getSchoolById(int id);

}