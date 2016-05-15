package services;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import entities.Course;
import entities.Grade;
import io.DatabaseFileReader;
import io.DatabaseFileWriter;


public class CourseOperations {

	public List<Course> getAllCourses(){
		return DatabaseFileReader.loadAllCoursesFromDB();
	}
	
	public Map<String,String> getAllCoursesAsMap(){
		return DatabaseFileReader.loadCoursesAsMap();
	}
	
	public void saveCourse(Course course){
		DatabaseFileWriter.saveCourseToFile(course);
	}
	
	public void removeCourse(Course course){
		DatabaseFileWriter.removeCourseFromFile(course);
	}
	
	public boolean checkIfPassedAtFirstTime(int studentId, int courseId){
		
		List<Course> courses = DatabaseFileReader.loadAllCoursesFromDB();

		List<Grade> grades = DatabaseFileReader.loadGradesForStudent(studentId);

		for (Grade grade : grades) {
			for (Course course : courses) {
				if (grade.getCourseId() == courseId && grade.getCourseId() == course.getId()
						&& grade.getYear() == course.getYear()) {
					return true;
				}

			}
		}

		return false;
	}
	
	public int getCreditsOfCourse(int courseId){

		List<Course> courses = DatabaseFileReader.loadAllCoursesFromDB();

		for (Course course : courses) {
			if (course.getId() == courseId) {
				return course.getCredit();
			}
		}
		
		return -1;
	}
	
	public void exportCoursesToFile(){
		
		try {
			PrintWriter pw = new PrintWriter(new FileOutputStream("courses-exported.tsv"));
			
			List<Course> courses = DatabaseFileReader.loadAllCoursesFromDB();

			for (Course course : courses) {
				pw.println(course.getId() + "\t" + course.getName() + "\t" + course.getCredit() + "\t" + course.getYear() + "\t" + course.getSemester() );
			}
			
			pw.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	 
}
