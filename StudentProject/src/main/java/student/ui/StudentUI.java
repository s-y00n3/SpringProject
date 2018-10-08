package student.ui;

import java.util.List;
import java.util.Scanner;

import student.domain.StudentVO;
import student.service.AlreadyExistingStudentException;
import student.service.StudentService;

//Presentation Layer
//사용자와 소통하는 Layer
//Business Layer에 의존
//우리 예제에서는 StudentService에 의존함
public class StudentUI {

	private StudentService studentService;

	//생성자를 이용한 StudentService 주입
	public StudentUI(StudentService studentService) {
		super();
		this.studentService = studentService;
	}

	//세터를 이용한 StudentService 주입
	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}
	
	public void showMenu() {
		Scanner scan = new Scanner(System.in);
		String command = "";
		
		while(true) {
			
			System.out.println("명령어를 입력하세요: ");
			command = scan.nextLine();
			
			if(command.equalsIgnoreCase("exit")) {
				System.out.println("프로그램을 종료합니다.");
				break;
			}else if(command.toLowerCase().startsWith("new ")) {
				processNewCommand(command.split(" ")); //빈칸 하나 꼭!
				continue;
			}else if(command.toLowerCase().startsWith("search ")) {
				processSearchCommand(command.split(" ")); //빈칸
				continue;
			}else if(command.toLowerCase().startsWith("change ")) {
				processChangeCommand(command.split(" "));
				continue;
			}else if(command.toLowerCase().startsWith("delete ")) {
				processDeleteCommand(command.split(" "));
				continue;
			}else if(command.equalsIgnoreCase("list")) {
				processListCommand();
				continue;
			}
			
			printHelp();
		}
	}
	
	private void processListCommand() {
		
		List<StudentVO> students
		= studentService.getStudents();
		
		for(StudentVO student : students) {
			System.out.println(student);
		}
		
	}

	private void processDeleteCommand(String[] args) {
		
		if(args.length != 2) {
			printHelp();
			return;
		}
		
		try {
			studentService.removeStudent(args[1]);
			System.out.println("학생을 삭제하였습니다.\n");
		} catch (Exception e) {
			System.out.println("학생이 존재하지 않습니다.\n");
		}
		
	}

	private void processChangeCommand(String[] args) {
		
		if(args.length != 5) {
			printHelp();
			return;
		}
		
		StudentVO student = new StudentVO();
		student.setId(args[1]);
		student.setName(args[2]);
		student.setEmail(args[3]);
		student.setPassword(args[4]);
		
		try {
			studentService.changeStudent(student);
			System.out.println("학생 정보를 변경하였습니다.\n");
		} catch (Exception e) {
			System.out.println("학생이 존재하지 않습니다.\n");
		}
		
	}

	private void processSearchCommand(String[] args) {
		
		if(args.length != 2) {
			printHelp();
			return;
		}
		
		try {
			StudentVO student
			= studentService.getStudent(args[1]);
			System.out.println(student);
			
		} catch (Exception e) {
			System.out.println("학생이 존재하지 않습니다.\n");
		}
		
	}

	private void processNewCommand(String[] args) {
		
		if(args.length != 5) {
			printHelp();
			return;
		}
		
		StudentVO student = new StudentVO();
		student.setId(args[1]);
		student.setName(args[2]);
		student.setEmail(args[3]);
		student.setPassword(args[4]);
		
		try {
			studentService.registStudent(student);
		}catch(AlreadyExistingStudentException e) {
			System.out.println("이미 존재하는 학생입니다.");
		}
	}

	private void printHelp() {
		System.out.println();
		System.out.println("잘못된 명령어입니다. 아래 명령어 사용법을 확인하세요.");
		System.out.println("명령어 사용법 : ");
		System.out.println("new  학생아이디  이름  비밀번호  이메일");
		System.out.println("search  학생아이디");
		System.out.println("change  학생아이디  변경할이름  변경할비밀번호  변경할이메일");
		System.out.println("delete  학생아이디");
		System.out.println("list");
		System.out.println();
	}
	
	
	
	
	
}
