package services;

import io.DatabaseFileWriter;
import entities.Grade;

public class GradeOperations {
	
	public void saveGrade(Grade grade){
		DatabaseFileWriter.saveGradeToFile(grade);
	}
}
