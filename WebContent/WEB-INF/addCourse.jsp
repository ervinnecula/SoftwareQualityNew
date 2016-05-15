<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
	<div class="jumbotron" style="width:60%;margin: 0 auto;">
		<ul class="pager">
		  <li class="previous"><a href="Main">&larr; Back</a></li>
		</ul>
		<form class="form-horizontal" action="AddCourse" method="post">
			<fieldset>
				<legend>Add course form</legend>
				
				<div class="form-group">
					<label for="id" class="col-lg-2 control-label">Id</label>
					<div class="col-lg-10">
						<input type="text" class="form-control" name="id"
							placeholder="100">
					</div>
				</div>
				<div class="form-group">
					<label for="name" class="col-lg-2 control-label">Name</label>
					<div class="col-lg-10">
						<input type="text" class="form-control" name="name"
							placeholder="Proiectarea Algoritmilor">
					</div>
				</div>
				<div class="form-group">
					<label for="semester" class="col-lg-2 control-label">Semester</label>
					<div class="col-lg-10">
						   <select class="form-control" name="semester">
					          <option>1</option>
					          <option>2</option>
					        </select>
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
				</div>
				<div class="form-group">
					<label for="credit" class="col-lg-2 control-label">Credit</label>
					<div class="col-lg-10">
						<input type="text" class="form-control" name="credit"
							placeholder="100">
					</div>
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