package student.service;

import java.util.List;

import student.domain.StudentVO;

//비즈니스 레이어
//실제 업무, 로직 처리
public interface StudentService {
	
	//학생 등록 서비스
	public void registStudent(StudentVO studentVO);
	
	//학생 조회 서비스
	public StudentVO getStudent(String id);
	
	//학생 정보 변경 서비스
	public void changeStudent(StudentVO studentVO);
	
	//학생 정보 삭제 서비스
	public void removeStudent(String id);
	
	//전체 학생 조회 서비스
	public List<StudentVO> getStudents();
}
