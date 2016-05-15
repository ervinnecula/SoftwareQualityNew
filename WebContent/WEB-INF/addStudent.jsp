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
<title>Add Student</title>
</head>
<body>
	<div class="jumbotron" style="width:60%;margin: 0 auto;">
		<ul class="pager">
		  <li class="previous"><a href="Main">&larr; Back</a></li>
		</ul>
		<form class="form-horizontal" action="AddStudent" method="post">
			<fieldset>
				<legend>Add student form</legend>
				
				<div class="form-group">
					<label for="id" class="col-lg-2 control-label">Id</label>
					<div class="col-lg-10">
						<input type="text" class="form-control" name="id"
							placeholder="132">
					</div>
				</div>
				<div class="form-group">
					<label for="name" class="col-lg-2 control-label">Name</label>
					<div class="col-lg-10">
						<input type="text" class="form-control" name="name"
							placeholder="Ionescu">
					</div>
				</div>
				<div class="form-group">
					<label for="startingYear" class="col-lg-2 control-label">Starting
						Year</label>
					<div class="col-lg-10">
						<input type="text" class="form-control" name="startingYear"
							placeholder="2013">
					</div>
				</div>
				<div class="form-group">
					<label for="admissionGrade" class="col-lg-2 control-label">Admission
						Grade</label>
					<div class="col-lg-10">
						<input type="text" class="form-control" name="admissionGrade"
							placeholder="9.5">
					</div>
				</div>

				<div class="form-group">
					<div class="col-lg-10 col-lg-offset-2">
						<button type="submit" class="btn btn-primary">Submit</button>
					</div>
				</div>

			</fieldset>
		</form>
		
	</div>
</body>
</html>