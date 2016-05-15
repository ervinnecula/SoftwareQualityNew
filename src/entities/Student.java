package entities;

import java.util.Comparator;

import services.StudentOperations;

public class Student implements Comparator<Student>, Comparable<Student> {

	private int id;
	private String name;
	private int startingYear;
	private double admissionGrade;
	
	public Student(int id, String name, int startingYear, double admissionGrade) {
		this.id = id;
		this.name = name;
		this.startingYear = startingYear;
		this.admissionGrade = admissionGrade;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getStartingYear() {
		return startingYear;
	}
	public void setStartingYear(int startingYear) {
		this.startingYear = startingYear;
	}
	public double getAdmissionGrade() {
		return admissionGrade;
	}
	public void setAdmissionGrade(double admissionGrade) {
		this.admissionGrade = admissionGrade;
	}
	
	public int compare(Student s1, Student s2){
		StudentOperations so = new StudentOperations();
		double o1 = so.calculateAverageGradeForStudent(s1.getId());
		double o2 = so.calculateAverageGradeForStudent(s2.getId());
		
		if(o1 < o2){ 
			return -1;	
		}
		else if(o1 == o2){
			return 0;
		}
		else return 1;
	}
	
	public int compareTo(Student s) {
		StudentOperations so = new StudentOperations();
		Double firstAverage = so.calculateAverageGradeForStudent(this.getId());
		Double secondAverage = so.calculateAverageGradeForStudent(s.getId());
		return secondAverage.compareTo(firstAverage);
	}
}
