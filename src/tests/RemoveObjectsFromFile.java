package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import entities.Course;
import services.CourseOperations;

public class RemoveObjectsFromFile {

	@Test
	public void removeCourseFromFile() {
		CourseOperations co = new CourseOperations();
		int originalSize = co.getAllCourses().size();
		
		Course testCourse = new Course(400,"Test Course Name",1,2,8);
		co.saveCourse(testCourse);
				
		assertEquals(originalSize + 1, co.getAllCourses().size());  
				
		co.removeCourse(testCourse);
		
		assertEquals(originalSize, co.getAllCourses().size());
	}

}
