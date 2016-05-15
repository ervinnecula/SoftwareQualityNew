package servlets;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Course;
import services.CourseOperations;

/**
 * Servlet implementation class AddCourse
 */
@WebServlet("/AddCourse")
public class AddCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("WEB-INF/addCourse.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {

			String name = (String) request.getParameter("name");

			String id = (String) request.getParameter("id");
			String year = (String) request.getParameter("year");
			String semester = (String) request.getParameter("semester");
			String credit = (String) request.getParameter("credit");
					
			// id
			assert Pattern.matches("[a-zA-Z]+", id) : "Not a valid id -> id contains letters";
			assert Integer.parseInt(id) < 0 && Integer.parseInt(id) > 999 : "Not a valid id -> range check";
			
			// name
			assert Pattern.matches("[0-9]+", name) : "Name contains numbers";
			
			// year
			assert Pattern.matches("[a-zA-Z]", year) : "Not a valid year -> year contains letters";
			assert Integer.parseInt(year) > 9999 || Integer.parseInt(year) < 1000 : "Not a valid year -> range check";
			
			// semester
			assert Pattern.matches("[a-zA-Z]", semester) : "Not a valid semester -> semester contains letters";
			assert Integer.parseInt(semester) > 3 || Integer.parseInt(semester) < 1 : "Not a valid semester -> range check";	
			
			// credit
			assert Pattern.matches("[a-zA-Z]", credit) : "Not a valid credit -> credit contains letters";
			
			
			int intId = Integer.parseInt(request.getParameter("id"));
			int intYear = Integer.parseInt(request.getParameter("year"));
			int intSemester = Integer.parseInt(request.getParameter("semester"));
			int intCredit = Integer.parseInt(request.getParameter("credit"));
			CourseOperations co = new CourseOperations();
			co.saveCourse(new Course(intId, name, intSemester, intYear, intCredit));
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (AssertionError e){
			e.printStackTrace();			
		}
		request.getRequestDispatcher("WEB-INF/addCourse.jsp").forward(request, response);
	}

}
