package servlets;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Course;
import entities.Grade;
import services.CourseOperations;
import services.GradeOperations;
import services.StudentOperations;

@WebServlet("/AddGrade")
public class AddGrade extends HttpServlet{

protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		CourseOperations courseOp = new CourseOperations();
		request.setAttribute("courses", courseOp.getAllCoursesAsMap());
		request.getRequestDispatcher("WEB-INF/addGrade.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {

			int studId = Integer.parseInt(request.getParameter("studId"));
			int courseId = Integer.parseInt(request.getParameter("courseId"));
			double grade = Double.parseDouble(request.getParameter("grade"));
			int year = Integer.parseInt(request.getParameter("year"));
			
			// id
			assert Pattern.matches("[a-zA-Z]+", studId+"") : "Not a valid student id -> student id contains letters";
			assert Pattern.matches("[a-zA-Z]+", courseId+"") : "Not a valid course id -> course id contains letters";
			assert studId < 0 && studId > 999 : "Not a valid student id -> range check";
			assert courseId < 0 && courseId > 999 : "Not a valid course id -> range check";

			// year
			assert grade < 0.0 || grade > 10.0 : "Not a valid year -> year contains letters";
			assert year > 9999 || year < 1000 : "Not a valid year -> range check";						
			

			GradeOperations gOp = new GradeOperations();
			gOp.saveGrade(new Grade(studId, courseId, grade, year));
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("WEB-INF/addGrade.jsp").forward(request, response);
	}
}
