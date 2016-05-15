package entities;

public class Grade {
	
	private int studentId;
	private int courseId;
	private double grade;
	private int year;
	
	public Grade(int studentId, int courseId, double grade, int year) {
		this.studentId = studentId;
		this.courseId = courseId;
		this.grade = grade;
		this.year = year;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public double getGrade() {
		return grade;
	}
	public void setGrade(double grade) {
		this.grade = grade;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}

}
