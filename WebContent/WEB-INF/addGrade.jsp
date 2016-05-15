<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<html>
<head>
<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/jquery-1.12.3.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/main.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div class="jumbotron" style="width: 60%; margin: 0 auto;">
		<ul class="pager">
			<li class="previous"><a href="Main">&larr; Back</a></li>
		</ul>
		<form class="form-horizontal" action="AddGrade" method="post">
			<fieldset>
				<legend>Add Grade for Student form</legend>

				<div class="form-group">
					<label for="studId" class="col-lg-2 control-label">StudentId</label>
					<div class="col-lg-10">
						<input type="text" class="form-control" name="studId"
							placeholder="100">
					</div>
				</div>
				<div class="form-group">
					<label for="courseId" class="col-lg-2 control-label">Course	Id</label> 
					<div class="col-lg-10">
						<select class="form-control" name='courseId'>
						<c:forEach items="${courses}" var="course">
							<option value="${course.key}">${course.value}</option>
						</c:forEach>
					</select>
					</div>
				</div>
				<div class="form-group">
					<label for="grade" class="col-lg-2 control-label">Grade</label>
					<div class="col-lg-10">
						<input type="text" class="form-control" name="grade"
							placeholder="5.5">
					</div>
				</div>
				<div class="form-group">
					<label for="year" class="col-lg-2 control-label">Year</label>
					<div class="col-lg-10">
						<select class="form-control" name="year">
							<option>1</option>
							<option>2</option>
							<option>3</option>
						</select>
					</div>
					
					<div class="form-group">
						<div class="col-lg-10 col-lg-offset-2">
							<button type="submit" class="btn btn-primary">Submit</button>
						</div>
					</div>
			</fieldset>
		</form>
</body>
</html>