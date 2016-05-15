package tests;

import static org.junit.Assert.assertEquals;

import java.util.regex.Pattern;

import org.junit.Before;
import org.junit.Test;

import entities.Course;
import entities.Student;
import services.CourseOperations;
import services.StudentOperations;

public class InputTest {

	StudentOperations so = new StudentOperations();
	CourseOperations co = new CourseOperations();
	Course course, course1;
	Student student, student1;
	
	@Before
	public void initialize() {
		course = new Course(300, "Fizica Informatica", 5, 5, 30);
		course1 = new Course(301, "Geografie", 1, 3, 40);
		student = new Student(11, "123", -1000, 999);
		student1 = new Student(12,"Vasile1", 1960, 5.99);
	}

	@Test
	public void studentValidationId() {

		boolean idCorrect = true;
	
		// id contains letters
		if (Pattern.matches(student.getId()+"","[a-zA-Z]+")) {
			idCorrect = false;
		} 
		assertEquals(true, idCorrect);
	}
	
	@Test
	public void studentValidationName(){
		boolean nameCorrect = true;
		
		if (Pattern.matches(student.getName(),"[1-9]+")) {
			nameCorrect = false;
		}
		assertEquals(true, nameCorrect);
	}
	
	@Test
	public void studentValidationYear(){
		
		boolean yearCorrect = true;
		
		if (Pattern.matches(student.getStartingYear()+"","[a-zA-Z]+") || student.getStartingYear() > 9999 ||
			student.getStartingYear() < 999) {
			yearCorrect = false;	
		}
		assertEquals(true, yearCorrect);
	}
	
	@Test
	public void studentValidationGrade(){
		  boolean gradeCorrect = true;
		  double grade = student.getAdmissionGrade();
		  if (Pattern.matches(grade+"","[a-zA-Z]+") || grade < 0.0 || grade > 10.0){
			  gradeCorrect = false;
		  }
		  assertEquals(true, gradeCorrect);
	}

	@Test
	public void courseValidationId() {

		boolean idCorrect = true;

		// id contains letters
		if (Pattern.matches(course.getId()+"","[a-zA-Z]+") || (course.getId() >  999 || course.getId() < 99)) {
			idCorrect = false;
		}
		assertEquals(true, idCorrect);
	}

	@Test
	public void courseValidationName(){
		
		boolean nameCorrect = true;
		// name contains numbers
		if(Pattern.matches(course.getName(),"[0-9]+")){
			nameCorrect = false;
		}
		assertEquals(true, nameCorrect);
	}
	
	@Test
	public void courseValidationSemester(){
		
		boolean semesterCorrect = true;
		
		if (course.getSemester() != 1 && course.getSemester() != 2){
			semesterCorrect = false;
		}
		assertEquals(true, semesterCorrect);
	}
	
	@Test
	public void courseValidationYear(){
		
		boolean yearCorrect = true;
		
		if (Pattern.matches(course.getYear()+"", "[a-zA-Z]+") || course.getYear() < 1 || course.getYear() > 3) {
			yearCorrect = false;
		}
		
		assertEquals(true, yearCorrect);
	}
	
	@Test
	public void courseValidationCredit(){
		
		boolean creditCorrect = true;
		
		if (Pattern.matches(course.getCredit()+"", "[a-zA-Z]+") && course.getCredit() < 0.0) {
			creditCorrect = false;
		}
		assertEquals(true, creditCorrect);
	}
	
}
