package entities;

public class Course {

	private int id;
	private String name;
	private int semester;
	private int year;
	private int credit; 
		
	public Course(int id, String name, int semester, int year, int credit) {
		
		this.id = id;
		this.name = name;
		this.semester = semester;
		this.year = year;
		this.credit = credit;
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
	public int getSemester() {
		return semester;
	}
	public void setSemester(int semester) {
		this.semester = semester;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getCredit() {
		return credit;
	}
	public void setPoints(int credit) {
		this.credit = credit;
	}
	
}
