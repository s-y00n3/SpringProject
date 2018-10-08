
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import student.ui.StudentUI;

public class Main {

	public static void main(String[] args) {
		ApplicationContext ctx
		= new GenericXmlApplicationContext("beans.xml");
		
		StudentUI studentUI
		= ctx.getBean("studentUI", StudentUI.class);
		
		studentUI.showMenu();
	}
}
