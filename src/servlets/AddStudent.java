package servlets;

import java.io.IOException;
import java.time.Year;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Student;
import services.StudentOperations;

/**
 * Servlet implementation class AddStudent
 */
@WebServlet("/AddStudent")
public class AddStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("WEB-INF/addStudent.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			String name = (String) request.getParameter("name");
			int startingYear = Integer.parseInt(request.getParameter("startingYear"));
			Double admissionGrade = Double.parseDouble(request.getParameter("admissionGrade"));

			assert Pattern.matches(".*[a-zA-Z].*", Integer.toString(id)) : "Id shouldn't contain letters.";
			assert Pattern.matches(".*[0-9].*", name) : "Student name shouldn't contain digits.";
			assert startingYear < 1900 || startingYear > Year.now().getValue() : "Not a valid year";
			assert admissionGrade < 0.0 || admissionGrade > 10.0 : "Not a valid admission grade."; 
			
			StudentOperations so = new StudentOperations();
			so.saveStudent(new Student(id, name, startingYear, admissionGrade));
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("WEB-INF/addStudent.jsp").forward(request, response);
	}

}