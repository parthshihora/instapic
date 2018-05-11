<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>All Post</title>
</head>
<body>


<div class="container">
	<div class="row">
	
	<div class="col-6 col-md-auto">
		<img src="${postObj.photoURL}" width="270px" height="270px" alt="My image"/>
		<audio autoplay>
			<source src="${postObj.audioURL}" type="audio/webm">
			</source>
		</audio>
	</div>
	<div class="col-6 col-md-auto">
		<h1 class="display-6">About Photo:</h1> <p>${postObj.text}</p>
	</div>
  		
	</div>
</div>
</body>
</html>