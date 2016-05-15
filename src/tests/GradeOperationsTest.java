package tests;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import entities.Course;
import entities.Grade;
import entities.Student;
import io.DatabaseFileReader;
import services.CourseOperations;
import services.GradeOperations;
import services.StudentOperations;

public class GradeOperationsTest {

	CourseOperations co = new CourseOperations();
	StudentOperations so = new StudentOperations();
	GradeOperations go = new GradeOperations();
	
	Course courseForTest;
	Student studForTest;
	Grade gradeForTest;
	
	@Before
	public void initialize(){
		courseForTest = new Course(300, "courseNameForTest", 1, 1, 5);
		studForTest = new Student(10, "studNameForTest", 2013, 8.9);
		gradeForTest = new Grade(studForTest.getId(), courseForTest.getId(), 8.0, 2014);
	}
	
	@Test
	public void test(){

		co.saveCourse(courseForTest);
		so.saveStudent(studForTest);
		go.saveGrade(gradeForTest);

		List<Grade> grades = DatabaseFileReader.loadGradesForStudent(studForTest.getId());
		Grade foundGr = null;
		for(Grade gr : grades) {
			if(gr.getCourseId() == courseForTest.getId() && gr.getYear() == 2014) {
				foundGr = gr;
			}
		}
		assertEquals(gradeForTest.getGrade(), foundGr.getGrade(),2);
	}
	

}
