package io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import database.DatabaseDetails;
import entities.Course;
import entities.Grade;
import entities.Student;

public class DatabaseFileReader {
	
	public static List<Student> loadAllStudentsFromDB(){

		List<Student> students = new ArrayList<Student>();
		
		try {

			BufferedReader br = new BufferedReader(new FileReader(
					DatabaseDetails.PATH_TO_STUDENTS_FILE));
			String fileLine = br.readLine();

			while (fileLine != null) {
				if (!fileLine.isEmpty()) {
					String[] splitResult = fileLine.split("\t");

					Student student = new Student(Integer.parseInt(splitResult[0]), //id
								    				splitResult[1], // name
								    			 Integer.parseInt(splitResult[2]), //starting year
								    			 Double.parseDouble(splitResult[3])); //admission grade

					students.add(student);
				}
				fileLine = br.readLine();

			    }
			    
		    br.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return students;
	}
	
	public static List<Course> loadAllCoursesFromDB(){

		List<Course> courses = new ArrayList<Course>();

		try {
			BufferedReader br = new BufferedReader(new FileReader(
					DatabaseDetails.PATH_TO_COURSES_FILE));
			String fileLine = br.readLine();

			while (fileLine != null) {
				if (!fileLine.isEmpty()) {
					String[] splitResult = fileLine.split("\t");

					Course course = new Course(Integer.parseInt(splitResult[0]), //id
											   splitResult[1], //name 
											   Integer.parseInt(splitResult[2]), //semester
											   Integer.parseInt(splitResult[3]), //year
											   Integer.parseInt(splitResult[4])); //credit

					courses.add(course);
				}
				fileLine = br.readLine();

			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return courses;
	}
	
	public static Map<String,String> loadCoursesAsMap(){

		Map<String,String> courses = new HashMap<String, String>();

		try {
			BufferedReader br = new BufferedReader(new FileReader(
					DatabaseDetails.PATH_TO_COURSES_FILE));
			String fileLine = br.readLine();

			while (fileLine != null) {
				if (!fileLine.isEmpty()) {
					String[] splitResult = fileLine.split("\t");

					courses.put(splitResult[0], splitResult[1]);
				}
				fileLine = br.readLine();
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return courses;
	}
	
	public static Student getStudentById(int id){
		
		List<Student> students = new ArrayList<Student>();
		students = loadAllStudentsFromDB();
		
		for(Student student: students){
			if(student.getId() == id){
				return student;
			}
		}
		return null;
	}

	public static List<Grade> loadGradesForStudent(int studentId){
		
		List<Grade> grades = new ArrayList<Grade>();
		boolean exists = false;
		
		try {

		BufferedReader br = new BufferedReader(new FileReader(DatabaseDetails.PATH_TO_GRADES_FILE));
		String fileLine = br.readLine();

		while (fileLine != null) {
			if (!fileLine.isEmpty()) {
				String[] splitResult = fileLine.split("\t");

				if (Integer.parseInt(splitResult[0]) == studentId) {

					Grade grade = new Grade(Integer.parseInt(splitResult[0]), // student
																				// id
							Integer.parseInt(splitResult[1]), // course id
							Double.parseDouble(splitResult[2]), // grade as
																// double
							Integer.parseInt(splitResult[3])); // year

					grades.add(grade);
					exists = true;
				}

			}
			fileLine = br.readLine();
		}
		br.close();

		if (exists == false) {
			grades = null;
		}
		
	} catch(IOException e){
		e.printStackTrace();
	}

		return grades;
	}
}
