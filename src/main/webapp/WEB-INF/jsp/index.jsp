<html>
<head>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" >
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
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
<br>
<br>

<form action="/upload" method="POST" enctype="multipart/form-data">
	<div class="container">
	<div class="form-row">
    <div class="form-group col-md-6">
		<label for="InputFile">Upload Image: </label>
		<input type="file" class="form-control" name="file"/>
	</div>
	</div>
	</div>
	<div class="container">
	<div class="form-row">
    <div class="form-group col-md-6">
		<button type="submit" class="btn btn-primary">Submit</button>
	</div>
	</div>
	</div>
</form>

</body>
</html>