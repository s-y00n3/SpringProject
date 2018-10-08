package com.student.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.student.domain.StudentVO;
import com.student.persistence.ReplyDAO;
import com.student.persistence.StudentDAO;

@Component
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentDAO studentDAO;
	
	@Autowired
	private ReplyDAO replyDAO;

	@Override
	public void registStudent(StudentVO studentVO) {

		StudentVO student = studentDAO.selectById(studentVO.getId());
		if (student != null) {
			throw new AlreadyExistingStudentException();
		}

		studentDAO.create(studentVO);
	}

	@Override
	public StudentVO getStudent(String id) {

		StudentVO student = studentDAO.selectById(id);
		
		if (student == null) {
			throw new StudentNotFoundException();
		}
		return student;
	}

	@Override
	public void changeStudent(StudentVO studentVO) {
		
		StudentVO student = studentDAO.selectById(studentVO.getId());

		if (student == null) {
			throw new StudentNotFoundException();
		}
		
		studentDAO.update(studentVO);
	}
	// 수정
	@Transactional
	@Override
	public void removeStudent(String id) {
		
		StudentVO student = studentDAO.selectById(id);
		
		if(student == null){
			throw new StudentNotFoundException();
		}
		studentDAO.delete(id);
		replyDAO.deleteRelies(id);
	}

	@Override
	public List<StudentVO> getStudents() {
		
		return studentDAO.selectAll();
	}

}
