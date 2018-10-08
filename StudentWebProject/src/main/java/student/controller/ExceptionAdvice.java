package student.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import student.service.AlreadyExistingStudentException;
import student.service.StudentNotFoundException;

@ControllerAdvice
public class ExceptionAdvice {

	@ExceptionHandler(AlreadyExistingStudentException.class)
	public String error1(Model model) {
		
		model.addAttribute("errorMessage", "이미 등록된 학생입니다.");
		return "error/error";
	}
	
	@ExceptionHandler(StudentNotFoundException.class)
	public String error2(Model model) {
		
		model.addAttribute("errorMessage", "학생이 등록되어 있지 않습니다.");
		return "error/error";
	}
}
