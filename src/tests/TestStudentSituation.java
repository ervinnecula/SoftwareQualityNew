package tests;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Before;
import org.junit.Test;

import database.DatabaseDetails;
import entities.States;
import entities.Student;
import services.GradeOperations;
import services.StudentOperations;

public class TestStudentSituation {
	
	StudentOperations so = new StudentOperations();
	GradeOperations go = new GradeOperations();
	Map<Integer, Double> studentGrades = new LinkedHashMap<Integer, Double>();
	Map<Integer, States> studentStatus = new TreeMap<Integer, States>();
	
	@Before
	public void initialize() {
		studentGrades.put(3,so.calculateAverageGradeForStudent(3));
		studentGrades.put(2,so.calculateAverageGradeForStudent(2));
		studentGrades.put(4,so.calculateAverageGradeForStudent(4));
		studentGrades.put(1,so.calculateAverageGradeForStudent(1));
		studentGrades.put(5,so.calculateAverageGradeForStudent(5));
		studentGrades.put(6,so.calculateAverageGradeForStudent(6));

	}
	
	@Test
	public void calculateAverageTest1() {
		assertEquals(6.370, so.calculateAverageGradeForStudent(1), 0.1);
	}
	
	@Test
	public void calculateAverageTest2() {
		assertEquals(-1, so.calculateAverageGradeForStudent(8), 0.1);
	}

	@Test
	public void numberSlotsTest() {
		studentStatus = so.calculateStateForStudents(so.getAllStudents());

		int scolarshipSlots = 0, budgetSlots = 0, taxSlots = 0;

		for (Map.Entry<Integer, States> entry : studentStatus.entrySet()) {

			switch (entry.getValue()) {
			case SCOLARSHIP: {
				scolarshipSlots++;
				break;
			}
			case BUDGET: {
				budgetSlots++;
				break;
			}
			case TAX: {
				taxSlots++;
				break;
			}
			default:
				break;
			}
		}

		assertEquals(DatabaseDetails.SLOTS_SCOLARSHIP, scolarshipSlots);
		assertEquals(DatabaseDetails.SLOTS_BUDGET, budgetSlots);
		assertEquals(DatabaseDetails.SLOTS_PAID, taxSlots);
	
	}
	
	
	@Test
	public void countStudentsTest(){
		
		List<Student> students = so.getAllStudents();
		int emptyLinesCount = 0, totalLinesCount = 0;
		try {
		final BufferedReader br = new BufferedReader(new FileReader(DatabaseDetails.PATH_TO_STUDENTS_FILE));
		String line;

			while((line=br.readLine()) != null){
				totalLinesCount++;
				if(line.trim().isEmpty()){
					emptyLinesCount++;
				}
			}
			
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		assertEquals(totalLinesCount - emptyLinesCount, students.size());
		
	}
	
	@Test
	public void getStudentByIdTest(){
		assertEquals("Ioana", so.getStudentById(5).getName());
	}
	
	@Test
	public void studentsOrderedCorrectlyTest(){
		List<Student> students = so.orderStudentsByAverage();
		int i = 0;
		boolean correct = true;
		
		for(Map.Entry<Integer, Double> studentGrade: studentGrades.entrySet()){
			if(!studentGrade.getKey().equals(students.get(i).getId())){
				correct = false;
			}
			i++;
		}
		
		assertEquals(correct, true);
	}
	
}
