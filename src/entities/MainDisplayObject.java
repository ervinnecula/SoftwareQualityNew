package entities;

import java.util.Comparator;

public class MainDisplayObject implements Comparator<MainDisplayObject>, Comparable<MainDisplayObject> {
	
	private Student student;
	private int credits;
	private int points;
	private double averageGrade;
	private States state;
	
	public MainDisplayObject(Student student, int credits, int points, double averageGrade, States state){
		this.student = student;
		this.credits = credits;
		this.points = points;
		this.averageGrade = averageGrade;
		this.state = state;
	} 

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public int getCredits() {
		return credits;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public double getAverageGrade() {
		return averageGrade;
	}

	public void setAverageGrade(double averageGrade) {
		this.averageGrade = averageGrade;
	}
	
	public States getState(){
		return this.state;
	}
	
	public void setState(States state){
		this.state = state;
	}
	
	public int compare(MainDisplayObject mdo, MainDisplayObject otherMdo){
		double o1 = mdo.getAverageGrade();
		double o2 = otherMdo.getAverageGrade();
		
		if(o1 < o2) return -1;
		else if(o1 == o2) return 0;
		else return 1;
	}

	@Override
	public int compareTo(MainDisplayObject o) {
		Double avg1 = this.getAverageGrade();
		Double avg2 = o.getAverageGrade();
		return avg2.compareTo(avg1);
	}
		
}
