package com.student.persistence;

import java.util.List;

import com.student.domain.StudentVO;

public interface StudentDAO {
	
	public void create(StudentVO studentVO);
	public StudentVO selectById(String id);
	public void update(StudentVO studentVO);
	public void delete(String id);
	public List<StudentVO> selectAll();
	
}
