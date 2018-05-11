<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" >
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>

<title>Insert title here</title>
</head>
<body>
<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" ></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" ></script>


<nav class="navbar navbar-dark bg-dark" >
  <a class="navbar-brand" href="#">
    <img src="logo.jpeg" width="30" height="30" class="d-inline-block align-top" alt="">
    INSTAPIC
  </a>
  
</nav>

<div class="container">
		<div class="row">
			<form action="/user/add" method="post" enctype="multipart/form-data">
				<div class="form-group">
					<label for="description">Name</label> 
					<input type="text" name="name" class="form-control" id="description"
						aria-describedby="desciptionHelp" placeholder="Enter Description"> 
				</div>
				<div class="form-group">
					<label for="description">About you</label> 
					<input type="text" name="description" class="form-control" id="description"
						aria-describedby="desciptionHelp" placeholder="Enter Description"> 
				</div>
				<div class="form-group">
					<label for="InputFile">Upload Profile Picture: </label>
					<input type="file" class="form-control" name="file"/>
				</div>
				
				<button type="submit" class="btn btn-primary">Submit</button>
			</form>
		</div>
	</div>

</body>
</html>