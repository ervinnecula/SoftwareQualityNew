package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.MainDisplayObject;
import entities.States;
import entities.Student;
import services.CourseOperations;
import services.StudentOperations;

/**
 * Servlet implementation class Main
 */
@WebServlet("/Main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		StudentOperations studOp = new StudentOperations();

		int[] credits = studOp.calculateCreditsForStudents();
		List<Student> students = studOp.getAllStudents();
		int[] points = studOp.calculatePointsForStudents();
		double[] averageGrades = studOp.calculateAverageGradeForStudents();
		Map<Integer, States> statesList = studOp.calculateStateForStudents(studOp.getAllStudents());

		List<MainDisplayObject> mdos = new ArrayList<MainDisplayObject>();

		for (int i = 0; i < students.size(); i++) {
			for (Map.Entry<Integer, States> stateObj : statesList.entrySet()) {
				if (stateObj.getKey().equals(students.get(i).getId())) {
					MainDisplayObject mdo = new MainDisplayObject(students.get(i), credits[i], points[i],
							averageGrades[i], stateObj.getValue());
					mdos.add(mdo);
				}
			}

		}

		request.setAttribute("mdos", mdos);

		List<MainDisplayObject> copyOfMdos = new ArrayList<MainDisplayObject>();
		copyOfMdos.addAll(mdos);

		CourseOperations courseOp = new CourseOperations();
		request.setAttribute("courses", courseOp.getAllCourses());

		Collections.sort(copyOfMdos);
		request.setAttribute("orderedMdos", copyOfMdos);

		request.getRequestDispatcher("WEB-INF/main.jsp").forward(request, response);
			


	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if (request.getParameter("button1") != null) {

			StudentOperations studOp = new StudentOperations();

			int[] credits = studOp.calculateCreditsForStudents();
			List<Student> students = studOp.getAllStudents();
			int[] points = studOp.calculatePointsForStudents();
			double[] averageGrades = studOp.calculateAverageGradeForStudents();
			Map<Integer, States> statesList = studOp.calculateStateForStudents(studOp.getAllStudents());
			List<MainDisplayObject> mdos = new ArrayList<MainDisplayObject>();

			for (int i = 0; i < students.size(); i++) {
				for (Map.Entry<Integer, States> stateObj : statesList.entrySet()) {
					if (stateObj.getKey().equals(students.get(i).getId())) {
						MainDisplayObject mdo = new MainDisplayObject(students.get(i),
																	  credits[i],
																	  points[i],
																	  averageGrades[i],
																	  stateObj.getValue());
						mdos.add(mdo);
					}
				}

			}

			studOp.exportSituationToFile(mdos);

		} else if (request.getParameter("button2") != null) {
			CourseOperations courseOp = new CourseOperations();
			courseOp.exportCoursesToFile();
		}

		this.doGet(request, response);

	}


}
