package com.yr.service;

import com.yr.dao.StudentDao;
import com.yr.entity.Page;
import com.yr.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentService {
	@Autowired
	private StudentDao studentDao;
	
	@Autowired
	private SchoolService schoolService;
	/**
	 * 分页查询
	 */
	@Transactional
	public void getList(Page<Student> page) {

		page.setSum(studentDao.getSum());
		page.setStart((page.getPageNo() - 1 ) * page.getPageSize());

		List<Student> students =studentDao.getList(page);
		page.setList(students);
		for (Student student:students
			 ) {
			System.out.println(student);
		}
		//查总数  放入sum

		//计算开始下标 放入
	}
	/**
	 * 添加数据
	 * 
	 * @param student
	 * @return
	 */
	@Transactional
	public int save(Student student){

		studentDao.save(student);
		return student.getId();
	}

	/**
	 * 根据id删除
	 * @param id
	 */
	@Transactional
	public void delete(int id) {
		studentDao.delete(id);
	}

	/**
	 * 获取id
	 * 
	 * @param id
	 * @return
	 */
	@Transactional
	public Student getStudentById(int  id) {
		return studentDao.getStudentById(id);
	}

	/**
	 * 修改学生
	 * 
	 * @param student
	 */
	@Transactional
	public void update(Student student) {
		student.setSchID(student.getSchool().getId());
		studentDao.update(student);
	}
}
