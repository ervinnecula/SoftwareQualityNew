<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/jquery-1.12.3.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/main.js"></script>
<title>Main Page</title>
</head>
<body>
	<nav class="navbar navbar-default"> 
	
		<ul class="nav nav-tabs">
			<li class="active"><a aria-expanded="false" href="#students" data-toggle="tab">Students</a></li>
			<li><a aria-expanded="true" href="#course" data-toggle="tab">Courses</a></li>
			<li><a aria-expanded="true" href="#situation" data-toggle="tab">Students situation</a></li>

			<a href="#" class="btn btn-default dropdown-toggle"
				data-toggle="dropdown" aria-expanded="false"><span class="caret"></span></a>
			<ul class="dropdown-menu">
				<li><a href="#">
						<form action="${pageContext.request.contextPath}/Main" method="post">
						    <input type="submit" name="button1" class="btn btn-success" value="Save situation" />
						    <input type="submit" name="button2" class="btn btn-success" value="Save courses" />
						</form>
					</a>
				</li>
			</ul>

		<ul class="nav navbar-nav navbar-right" style="margin-right: 30px;">
				<li><a href="AddStudent">Add Student</a></li>
				<li><a href="AddCourse">Add Course</a></li>
				<li><a href="AddGrade">Add Grade</a></li>
			</ul>
			
		</ul>
	
		<div class="tab-content" id="myTabContent">
			<div class="tab-pane fade active in" id="students">
				<table class="table table-striped table-hover ">
				 <thead>
					    <tr>
					      <th>Id</th>
					      <th>Name</th>
					      <th>Start Year</th>
					      <th>Admission Grade</th>
					      <th>Average Grade</th>
					      <th>Credits</th>
					      <th>Points</th>
					    </tr>
					  </thead>
					  <tbody>
					<c:forEach items="${mdos}" var="item">
					    <tr>
					    	<td><c:out value="${item.student.id}"/></td>  
					    	<td><c:out value="${item.student.name}"/></td>  
					    	<td><c:out value="${item.student.startingYear}"/></td>  
					    	<td><c:out value="${item.student.admissionGrade}"/></td>
					    	<td><c:out value="${item.averageGrade}"/></td> 
					    	<td><c:out value="${item.credits}"/></td>
					    	<td><c:out value="${item.points}"/></td>
					    </tr>
					</c:forEach>
					  </tbody> 
					</table> 
			</div>
			<div class="tab-pane fade" id="course">
			<table class="table table-striped table-hover ">
				<thead>
					<tr>
						<th>Id</th>
						<th>Name</th>
						<th>Semester</th>
						<th>Year</th>
						<th>Credit</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${courses}" var="course">
						<tr>
							<td><c:out value="${course.id}" /></td>
							<td><c:out value="${course.name}" /></td>
							<td><c:out value="${course.year}" /></td>
							<td><c:out value="${course.semester}" /></td>
							<td><c:out value="${course.credit}" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		
		<div class="tab-pane fade" id="situation">
			<table class="table table-striped table-hover ">
				<thead>
					<tr>
						<th>Id</th>
						<th>Name</th>
						<th>Average Grade</th>
						<th>Credits</th>
						<th>Points</th>
						<th>State</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${orderedMdos}" var="item">
						<tr>
					    	<td><c:out value="${item.student.id}"/></td>  
					    	<td><c:out value="${item.student.name}"/></td>  
					    	<td><c:out value="${item.averageGrade}"/></td> 
					    	<td><c:out value="${item.credits}"/></td>
					    	<td><c:out value="${item.points}"/></td>
					    	<td><c:out value="${item.state}"/></td>
					    </tr>
					</c:forEach>
				</tbody>
			</table>
		
		</div>
	</nav>
	
</body>
</html>