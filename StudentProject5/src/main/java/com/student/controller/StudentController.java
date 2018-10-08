package com.student.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.student.domain.StudentVO;
import com.student.service.StudentService;

@Controller
@RequestMapping("/student")
public class StudentController {
	// 학생 삭제시 관련 댓글 모두 삭제
	// 트랜젝션 처리

	@Autowired
	private StudentService studentService;

	@Resource(name = "uploadPath")
	private String uploadPath;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String listAll(Model model) {

		List<StudentVO> students = studentService.getStudents();
		model.addAttribute("students", students);

		return "student/list";
	}
	
	@RequestMapping(value="/imageList", method=RequestMethod.GET)
	public String imageListAll(Model model){
		
		List<StudentVO> students = studentService.getStudents();
		model.addAttribute("students", students);
		
		
		return "student/imageList";
	}

	@RequestMapping(value = "register", method = RequestMethod.GET)
	public String registerGet() {
		return "student/register";
	}

	@RequestMapping(value = "register", method = RequestMethod.POST)
	public String registerPost(StudentVO studentVO) throws Exception {
		System.out.println("register");

		MultipartFile file = studentVO.getFile();

		// 업로드된 파일이 있으면
		if(file.getSize() > 0) {
			String savedName = uploadFile(file.getOriginalFilename(), file.getBytes());
			studentVO.setSavedName(savedName);
		}

		studentService.registStudent(studentVO);

		return "redirect:/student/list";
	}

	private String uploadFile(String originalName, byte[] fileData) throws Exception {

		UUID uid = UUID.randomUUID();
		String savedName = uid.toString() + "_" + originalName;
		File target = new File(uploadPath, savedName);
		FileCopyUtils.copy(fileData, target);

		return savedName;
	}

	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public void read(@RequestParam("id") String id, Model model) {

		StudentVO student = studentService.getStudent(id);

		String savedName = student.getSavedName();
		String originalName = null;
		if(savedName != null)
			originalName = savedName.substring(savedName.indexOf("_") + 1);
		model.addAttribute("originalName", originalName);
		model.addAttribute("student", student);
	}

	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public String modifyGet(@RequestParam("id") String id, Model model) {

		StudentVO student = studentService.getStudent(id);
		model.addAttribute("student", student);

		return "student/modify";
	}

	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modifyPost(StudentVO studentVO, Model model) throws Exception {

		MultipartFile file = studentVO.getFile();
		StudentVO oldStudent = studentService.getStudent(studentVO.getId());
		
		if (file.getSize() != 0) {
			String savedName = uploadFile(file.getOriginalFilename(), file.getBytes());
			studentVO.setSavedName(savedName);			
			String oldFileName = oldStudent.getSavedName();
			
			if(oldFileName != null)
				new File(uploadPath, oldFileName).delete();
		}
//		}else {
//			StudentVO oldStudent = studentService.getStudent(studentVO.getId());
//			String oldFileName = oldStudent.getSavedName();
//			
//			if(oldFileName != null && !oldFileName.equals(""))
//				new File(uploadPath, oldFileName).delete();
//			studentVO.setSavedName("");
//		}
		else {
			String savedName = oldStudent.getSavedName();
			studentVO.setSavedName(savedName);	
		}
		studentService.changeStudent(studentVO);
		
		return "redirect:/student/list";		
	}

	@RequestMapping(value = "/remove", method = RequestMethod.GET)
	public String removeGet(@RequestParam("id") String id) {

		StudentVO oldStudent = studentService.getStudent(id);
		String oldFileName = oldStudent.getSavedName();
		
		if(oldFileName != null && !oldFileName.equals(""))
			new File(uploadPath, oldFileName).delete();
		
		studentService.removeStudent(id);

		return "redirect:/student/list";
	}

	@ResponseBody
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ResponseEntity<byte[]> displayFile(String fileName) throws Exception {

		InputStream in = null;
		ResponseEntity<byte[]> entity = null;

		System.out.println("fileName:" + fileName);
		try {
			String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);
			MediaType mType = getMediaType(formatName);
			HttpHeaders headers = new HttpHeaders();
			in = new FileInputStream(uploadPath + "\\" + fileName);

			if (mType != null) {
				headers.setContentType(mType);
			} else {

				fileName = fileName.substring(fileName.indexOf("_") + 1);
				headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
				headers.add("Content-Disposition",
						"attachment; filename=\"" + new String(fileName.getBytes("UTF-8"), "ISO-8859-1") + "\"");
			}

			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		} finally {
			in.close();
		}
		return entity;

	}

	private static Map<String, MediaType> mediaMap;
	static {

		mediaMap = new HashMap<String, MediaType>();
		mediaMap.put("JPG", MediaType.IMAGE_JPEG);
		mediaMap.put("JPEG", MediaType.IMAGE_JPEG);
		mediaMap.put("GIF", MediaType.IMAGE_GIF);
		mediaMap.put("PNG", MediaType.IMAGE_PNG);
	}

	private MediaType getMediaType(String type) {

		return mediaMap.get(type.toUpperCase());
	}
}
