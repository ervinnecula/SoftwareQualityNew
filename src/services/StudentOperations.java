package services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import database.DatabaseDetails;
import entities.Grade;
import entities.MainDisplayObject;
import entities.States;
import entities.Student;
import io.DatabaseFileReader;
import io.DatabaseFileWriter;

public class StudentOperations {

	public Student getStudentById(int id){
		return DatabaseFileReader.getStudentById(id);
	}
	
	public List<Student> getAllStudents(){
		return DatabaseFileReader.loadAllStudentsFromDB();
	}
		
	public void saveStudent(Student student){
		DatabaseFileWriter.saveStudentToFile(student);
	}
	
	public int calculatePointsForStudent(int studentId){

		int points = 0;

		List<Grade> grades = DatabaseFileReader.loadGradesForStudent(studentId);

		CourseOperations co = new CourseOperations();

		for (Grade grade : grades) {
			if (grade.getGrade() > 5.0 && co.checkIfPassedAtFirstTime(studentId, grade.getCourseId())) {
				points = points + (int) grade.getGrade() * co.getCreditsOfCourse(grade.getCourseId());
			}
		}
		return points;
	}
	
	public int calculateCreditsForStudent(int studentId) {
		int credits = 0;
		
		List<Grade> grades = DatabaseFileReader.loadGradesForStudent(studentId);

		CourseOperations co = new CourseOperations();

		for (Grade grade : grades) {
			if (grade.getGrade() >= 5.0 && co.checkIfPassedAtFirstTime(studentId, grade.getCourseId())) {
				credits = credits + co.getCreditsOfCourse(grade.getCourseId());
			}
		}

		return credits;
	}
	
	public int[] calculateCreditsForStudents() {
		List<Student> students = DatabaseFileReader.loadAllStudentsFromDB();
		int[] creditList = new int[students.size()];
		int i = 0;
		for(Student student:students){ 
			int credits = calculateCreditsForStudent(student.getId());
			creditList[i] = credits;
			i++;
		}
		return creditList;
	}
	
	public int[] calculatePointsForStudents() {
		List<Student> students = DatabaseFileReader.loadAllStudentsFromDB();
		int[] pointsList = new int[students.size()];
		int i = 0;
		
		for(Student student:students){
			int points = calculatePointsForStudent(student.getId());
			pointsList[i] = points;
			i++;
		}
		return pointsList;
	}
	
	public double calculateAverageGradeForStudent(int studentId) {

		double totalOfGrades = 0.0;
		int counter = 0;
		double average = 0.0;

		List<Grade> gradesOfStudent = DatabaseFileReader.loadGradesForStudent(studentId);
		
		if(gradesOfStudent != null){
			for (Grade grade : gradesOfStudent) {
				totalOfGrades += grade.getGrade();
				counter++;
			}
			average = totalOfGrades / counter;
			return average;
		}
		assert average > 10.0 || average < 0.0 : "Average grade is wrong -> range check";

		return -1;

	}
	
	public double[] calculateAverageGradeForStudents(){
		
		List<Student> studentList = DatabaseFileReader.loadAllStudentsFromDB();
		
		double[] averageGrades = new double[studentList.size()];
		int i = 0;
		
		for(Student student: studentList){
			double average = calculateAverageGradeForStudent(student.getId());
			averageGrades[i] = average;
			i++;
		}
		
		return averageGrades;
	}
	
	public Map<Integer, States> calculateStateForStudents(List<Student> allStudents){
		
		Collections.sort(allStudents);
		int countSlotsScolarship = 0,countSlotsBudget = 0,countSlotsTax = 0;
	
		Map<Integer, States> studentState = new TreeMap<Integer, States>();
		
		for(Student student:allStudents){
			if(calculateCreditsForStudent(student.getId()) >= 30 && countSlotsScolarship < DatabaseDetails.SLOTS_SCOLARSHIP){
				studentState.put(student.getId(), States.SCOLARSHIP);
				countSlotsScolarship++;
			}
			else if(calculateCreditsForStudent(student.getId()) >= 30 && countSlotsBudget < DatabaseDetails.SLOTS_BUDGET){
				studentState.put(student.getId(), States.BUDGET);
				countSlotsBudget++;
			}

			else if(calculateCreditsForStudent(student.getId()) >= 30 && countSlotsTax < DatabaseDetails.SLOTS_PAID){
				studentState.put(student.getId(), States.TAX);
				countSlotsTax++;

			}
			else{
				studentState.put(student.getId(), States.REPEATYEAR);			
			}
		}
		
		assert countSlotsScolarship != DatabaseDetails.SLOTS_SCOLARSHIP : "Program distributed scolarship slots wrong";
		assert countSlotsBudget != DatabaseDetails.SLOTS_BUDGET : "Program distributed budget slots wrong";
		assert countSlotsTax != DatabaseDetails.SLOTS_PAID : "Program distributed tax slots wrong";

		return studentState;
	}
	
	public List<Student> orderStudentsByAverage(){
		
		List<Student> students = getAllStudents();
		Collections.sort(students);
		return students;
	}
		
	public void exportSituationToFile(List<MainDisplayObject> mdos) {
		try {
			PrintWriter pw = new PrintWriter(new FileOutputStream(DatabaseDetails.EXPORT_PATH + "export situation.tsv"));

			pw.println("ID" + "\t" + "NAME" + "\t" + "AVG.GRADE" + "\t" + "CREDITS" + "\t" + "POINTS" + "\t");
			for (MainDisplayObject mdo : mdos) {
				pw.println(mdo.getStudent().getId() + "\t" + mdo.getStudent().getName() + "\t" + mdo.getAverageGrade() + "\t" + mdo.getCredits() + "\t"
						+ mdo.getPoints() + "\t" + mdo.getState());
			pw.flush();
			}
			pw.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		File f = new File(DatabaseDetails.EXPORT_PATH + "export situation.tsv");
		assert !f.exists() : "file does not exist";
	}
	
	public void exportStudentToFile(int studentId, List<MainDisplayObject> mdos){
		
		try {
			PrintWriter pw = new PrintWriter(new FileOutputStream(DatabaseDetails.EXPORT_PATH + "situation student " + studentId + ".tsv"));
		
			for (MainDisplayObject mdo : mdos) {
				if (mdo.getStudent().getId() == studentId) {
					pw.println(mdo.getStudent().getId() 
							   + "\t" + mdo.getStudent().getName()
							   + "\t" + mdo.getAverageGrade()
							   + "\t" + mdo.getCredits()
							   + "\t" + mdo.getPoints()
							   + "\t" + mdo.getState());
				}
			}
			pw.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

}
