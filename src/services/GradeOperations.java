package services;

import java.time.Year;
import java.util.regex.Pattern;

import io.DatabaseFileWriter;
import entities.Grade;

public class GradeOperations {
	
	public void saveGrade(Grade grade){
		assert Pattern.matches(".*[a-zA-Z].*", Integer.toString(grade.getStudentId())) : "Student id shouldn't contain letters.";
		assert Pattern.matches(".*[a-zA-Z].*", Integer.toString(grade.getCourseId())) : "Course id shouldn't contain letters.";
		assert grade.getGrade() < 0.0 || grade.getGrade() > 10.0 : "Invalid range for grade ";
		assert grade.getYear() < 1900 || grade.getYear() > Year.now().getValue() : "Not a valid year";
		DatabaseFileWriter.saveGradeToFile(grade);
	}
}
