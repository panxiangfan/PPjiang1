package com.yr.dao;

import com.yr.entity.Page;
import com.yr.entity.Student;

import java.util.List;


public interface StudentDao {


	/**
	 * 查询所有student 添加分页效果
	 * @return
	 */
	public List<Student> getList(Page<Student> page);

	
	/**
	 * 查询id
	 * @param id
	 * @return
	 */
	public Student getStudentById(int id);

	///查询总数
	public  Long getSum();


	// 新增用户.需要把新增的ID返回给界面使用
	public int save(Student student);


	// 根据id删除
	public void delete(int id);


	//修改 根据id
	public void update(Student student);

}