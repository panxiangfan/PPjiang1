package com.yr.service;

import java.util.List;

import com.yr.dao.SchoolDao;
import com.yr.entity.School;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SchoolService {
	
	@Autowired
	private SchoolDao schoolDao;
	
	/**
	 * 查询所有
	 * @return
	 */
	@Transactional
	public List<School> query() {
		return schoolDao.query();
	}
	
	/**
//	 * 查询id
//	 * @param id
//	 * @return
//	 */
	@Transactional
	public School getSchoolById(int id) {
		return schoolDao.getSchoolById(id);
	}
}
