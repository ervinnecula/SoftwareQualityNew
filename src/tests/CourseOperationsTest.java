package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import entities.Course;
import entities.Grade;
import entities.Student;
import services.CourseOperations;
import services.GradeOperations;
import services.StudentOperations;

@RunWith(JUnit4.class)
public class CourseOperationsTest {

	Course courseForTest1;
	Course courseForTest2;
	Course courseForTest3;
	Course courseForTest4;
	
	Student studForTest;
	Grade grade1;
	Grade grade2;
	
	
	CourseOperations co = new CourseOperations();
	StudentOperations so = new StudentOperations();
	GradeOperations go = new GradeOperations();
	
	int nonExistingCourseId = 500;

	
	@Before
	public void initialize(){
		courseForTest1 = new Course(301, "courseNameForTest1", 1, 1, 5);
		courseForTest2 = new Course(302, "courseNameForTest2", 2, 2, 5);
		courseForTest3 = new Course(303, "courseNameForTest3", 1, 1, 5);
		courseForTest4 = new Course(304, "courseNameForTest4", 1, 1, 50);
		
		studForTest = new Student(1, "studNameForTest", 2013, 8.9);
		grade1 = new Grade(studForTest.getId(), courseForTest3.getId(), 4.0, 2013);
		grade2 = new Grade(studForTest.getId(), courseForTest3.getId(), 6.5, 2014);
	}
	
	@Test
	public void testSaveCourse() {

		co.saveCourse(courseForTest1);
		co.saveCourse(courseForTest2);
		
		Map<String,String> cMap = co.getAllCoursesAsMap();
		
		if(!cMap.containsKey(courseForTest1.getId()+"") || !cMap.containsKey(courseForTest2.getId()+"")){
			fail("Courses were not saved correctly.");
		}
	}

	@Test
	public void testCheckIfPassedAtFirstTime() {	
		co.saveCourse(courseForTest3);
		so.saveStudent(studForTest);
		go.saveGrade(grade1);
		go.saveGrade(grade2);
		
		assertEquals(false, co.checkIfPassedAtFirstTime(studForTest.getId(), courseForTest3.getId()));	
	}
	
	@Test
	public void testGetCreditsOfCourse(){
		co.saveCourse(courseForTest4);
		
		//assertEquals(304, co.getCreditsOfCourse(courseForTest4.getId()));
		 co.getCreditsOfCourse(nonExistingCourseId);	
	}

	@Test
	public void testExportCoursesToFile() {
		fail("Not yet implemented");
	}

}
