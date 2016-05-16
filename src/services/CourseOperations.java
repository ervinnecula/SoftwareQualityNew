package services;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

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
		assert Pattern.matches(".*[a-zA-Z].*", Integer.toString(course.getId())) : "Id shouldn't contain letters.";
		assert course.getId() > 999 || course.getId() < 0 : "Invalid range for course id ";
		assert Pattern.matches(".*[0-9].*", course.getName()) : "Course name shouldn't contain digits.";
		assert course.getYear() < 1 || course.getYear() > 3 : "Not a valid academic year";
		assert course.getSemester() < 1 || course.getSemester() > 2 : "Not a valid semester."; 
		DatabaseFileWriter.saveCourseToFile(course);
	}
	
	public void removeCourse(Course course){
		DatabaseFileWriter.removeCourseFromFile(course);
	}
	
	public boolean checkIfPassedAtFirstTime(int studentId, int courseId){
		
		List<Course> courses = DatabaseFileReader.loadAllCoursesFromDB();

		List<Grade> grades = DatabaseFileReader.loadGradesForStudent(studentId);

		assert grades == null || grades.size() ==0 : "No grades were found for student";
		
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

		assert courses == null || courses.size() ==0 : "No courses were found in DB";
		
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
			
			assert courses == null || courses.size() ==0 : "No courses were found in DB";
			
			for (Course course : courses) {
				pw.println(course.getId() + "\t" + course.getName() + "\t" + course.getCredit() + "\t" + course.getYear() + "\t" + course.getSemester() );
			}
			
			pw.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	 
}
