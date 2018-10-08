package com.student.service;

import java.util.List;

import com.student.domain.StudentVO;

public interface StudentService {

	public void registStudent(StudentVO studentVO);

	public StudentVO getStudent(String id);

	public void changeStudent(StudentVO studentVO);

	public void removeStudent(String id);

	public List<StudentVO> getStudents();
}
