package student.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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

import student.domain.StudentVO;
import student.service.StudentService;

@Controller
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentService studentService;

	// 업로드된 파일을 저장할 경로를 주입함
	@Resource(name = "uploadPath")
	private String uploadPath;

	@RequestMapping(value = "/imageList", method = RequestMethod.GET)
	public String imageListAll(Model model) {
		List<StudentVO> students = studentService.getStudents();
		model.addAttribute("students", students);

		return "student/imageList";
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String listAll(Model model) {

		List<StudentVO> students = studentService.getStudents();
		model.addAttribute("students", students);

		return "student/list";
	}

	@RequestMapping(value = "register", method = RequestMethod.GET)
	public String registerGet() {
		return "student/register";
	}

	@RequestMapping(value = "register", method = RequestMethod.POST)
	public String registerPost(StudentVO studentVO) throws Exception, Exception {
		
		//추가
		MultipartFile file = studentVO.getFile();
		//업로드된 파일이 있으면
		if(file.getSize() > 0) {
			String savedName
			= uploadFile(file.getOriginalFilename(), file.getBytes());
			studentVO.setSavedName(savedName);
		}
		
		studentService.registStudent(studentVO);

		return "redirect:/student/list";
	}

	@RequestMapping(value = "read", method = RequestMethod.GET)
	public String read(@RequestParam("id") String id, Model model) {

		StudentVO studentVO = studentService.getStudent(id);
		//추가
		String savedName = studentVO.getSavedName();
		String originalName = null;
		if(savedName != null) {
			originalName = savedName.substring(savedName.indexOf("_") + 1);
			
		}
		
		model.addAttribute("originalName", originalName);
		
		model.addAttribute("student", studentVO);

		return "student/read";
	}

	@RequestMapping(value = "modify", method = RequestMethod.GET)
	public String modifyGet(@RequestParam("id") String id, Model model) {
		StudentVO student = studentService.getStudent(id);
		model.addAttribute("student", student);

		return "student/modify";
	}

	@RequestMapping(value = "modify", method = RequestMethod.POST)
	public String modifyPost(StudentVO studentVO) throws IOException, Exception {

		//추가
		//클라이언트가 수정하기 위해 새로 업로드한 사진 파일 받아오기
		MultipartFile file = studentVO.getFile();
		StudentVO oldStudent
		= studentService.getStudent(studentVO.getId());
		
		//수정하기 위해 업로드한 파일이 있다면
		if(file.getSize() != 0) {
			String savedName
			= uploadFile(file.getOriginalFilename(), file.getBytes());
			studentVO.setSavedName(savedName);
			//이전에 등록된 사진파일이름
			String oldFileName = oldStudent.getSavedName();
			
			//이전에 등록된 사진파일이 있다면
			if(oldFileName != null) {
				new File(uploadPath, oldFileName).delete(); //기존파일삭제
			}
		}
		//수정하기 위해 업로드된 파일이 없다면
		//기존에 등록된 파일명 그대로 사용
		else {
			String savedName = oldStudent.getSavedName();
			studentVO.setSavedName(savedName);
		}
		
		studentService.changeStudent(studentVO);

		return "redirect:/student/list";
	}

	@RequestMapping(value = "remove", method = RequestMethod.GET)
	public String removeGet(@RequestParam("id") String id) {
		
		//추가
		StudentVO oldStudent = studentService.getStudent(id);
		String oldFileName = oldStudent.getSavedName();
		
		//기존에 등록된 사진파일이 있으면 삭제
		if(oldFileName != null && !oldFileName.equals("")) {
			new File(uploadPath, oldFileName).delete();
		}
		
		//

		studentService.removeStudent(id);

		return "redirect:/student/list";
	}

	// 서버에 저장될 파일의 이름을 만들고
	// 클라이언트가 업로드한파일(byte[] bytes)을 서버의 특정 폴더에 저장함
	private String uploadFile(String originalFilename, byte[] bytes) throws Exception {

		// UUID : 중복되지 않는 고유한 키값을 설정함
		UUID uid = UUID.randomUUID();
		String savedName = uid.toString() + "_" + originalFilename;
		System.out.println("savedName: " + savedName);

		File target = new File(uploadPath, savedName);
		// 업로드된 파일 내용 bytes를 target파일에 복사
		// 즉, 클라이언트가 업로드한 내용이 서버에 저장됨
		FileCopyUtils.copy(bytes, target);

		return savedName;
	}

	private static Map<String, MediaType> mediaType;

	static {
		mediaType = new HashMap<String, MediaType>();
		mediaType.put("JPG", MediaType.IMAGE_JPEG);
		mediaType.put("JPEG", MediaType.IMAGE_JPEG);
		mediaType.put("GIF", MediaType.IMAGE_GIF);
		mediaType.put("PNG", MediaType.IMAGE_PNG);
	}

	private MediaType getMediaType(String type) {
		return mediaType.get(type.toUpperCase());
	}

	// 반환되는 것이 jsp가 아니라
	// 또는 반환되는 내용이 jsp같은 뷰를 만들어 내지 않고 대신,
	// 데이터 자체를 반환되는 데이터는 단순문자열, JSON, XML등이다.
	// 근데 우리 예제에서는 데이터는 파일내용자체임
	// ResponseEntity 타입은 결과 데이터와 HTTP상태코드를 함께 클라이언트로 보냄
	@ResponseBody
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ResponseEntity<byte[]> displayFile(String fileName) throws IOException {

		InputStream in = null;
		ResponseEntity<byte[]> entity = null;

		System.out.println("fileName: " + fileName);

		try {
			// 파일의 확장자 찾기
			String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);
			// 이미지 파일일 경우 이미지 파일의 타입 찾기
			MediaType mType = getMediaType(formatName);
			HttpHeaders headers = new HttpHeaders();
			// 서버에 저장된 파일을 읽을 수 있는 입력스트림 생성
			in = new FileInputStream(uploadPath + "\\" + fileName);

			if (mType != null) {
				// 이미지 파일일 경우 헤더에 mime타입 세팅
				headers.setContentType(mType);
			} else {
				// 이미지 파일이 아닐 경우, 브라우저에서 파일을 다운받을 수 있게 세팅
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

}
