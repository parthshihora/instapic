<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" >
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<title>Notification</title>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
		
		<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.12/css/all.css" integrity="sha384-G0fIWCsCzJIMAVNQPfjH08cyYaUtMwjJwqiRKxxE/rx96Uroj1BtIQ6MLJuheaO9" crossorigin="anonymous">
		


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
  <form class="form-inline my-2 my-lg-0">
 <!--   <a class="btn btn-outline-success my-2 my-sm-0 link " href="/myfriends">View Friends</a>
  <a class="btn btn-outline-success my-2 my-sm-0 link " href="/editprofile">Edit Profile</a>
   <a class="btn btn-outline-success my-2 my-sm-0 link " href="/postcreation">Post</a>
    <a class="btn btn-outline-success my-2 my-sm-0 link " href="/notification">Notification</a> -->
    
    
    <a  href="/notification"> <i class="fa fa-bell" style="font-size:36px" ></i></a>&nbsp;&nbsp;&nbsp;&nbsp;
 <a  href="/myfriends"> <i class="fas fa-user-friends" style="font-size:36px" ></i></a>&nbsp;&nbsp;&nbsp;&nbsp;
 <a  href="/editprofile"><i class="far fa-edit" style="font-size:36px"  ></i></a>&nbsp;&nbsp;&nbsp;&nbsp;
 <a  href="/postcreation"><i class="fa fa-video-camera" style="font-size:36px" ></i></a>&nbsp;&nbsp;&nbsp;&nbsp;
 
 <a href="/myprofile"> <i class="fas fa-home"  style="font-size:36px" ></i></a>  
    
    
  </form>
 </nav>
<br>
<br>




<script>
	function submitForm(index){
		var form = document.getElementById("id-"+index);
		form.submit()
	}
</script>

<ul class="list-group">
		<c:forEach items="${notifications}" var="notification" varStatus="loop">
		
		<c:if test="${empty notification.commentObj}">
			<form id="id-${loop.index}" action="/friendpost" method="post">
			<li class="list-group-item">
				<p>Your friend ${notification.postObj.userObj.userName} created post</p>
	<!--  	 		<p>${notification.postObj.id}</p> -->
		 		<img src="${notification.postObj.photoURL}" onclick="submitForm('${loop.index}');"  class="img-thumbnail" width="80px" height="80px"  alt="My image"/>
		 		<input type="hidden" name="Id" value="${notification.postObj.id}"/>
		 		<input type="hidden" name="friendId" value="${notification.postObj.userObj.userId}"/>
		 		<input type="hidden" name="comment" value="NULL">
		 	</li>
			</form>
		</c:if>
		
		</c:forEach>
	</ul>

		
		
<ul class="list-group">	
	<c:forEach items="${directNoti}" var="dnotification" varStatus="loop">
	
	<c:if test="${not empty dnotification.commentObj}">
			<form id="id-${loop.index}" action="/mypost" method="post">
			<li class="list-group-item">
				<p>Your friend ${dnotification.commentObj.userObj.userName} commented on your post</p>
		 <!--  		<p>${notification.postObj.id}</p> -->
		 		<img src="${dnotification.postObj.photoURL}" onclick="submitForm('${loop.index}');" class="img-thumbnail" width="80px" height="80px"  alt="My image"/>
		 		<input type="hidden" name="Id" value="${dnotification.postObj.id}"/>
		 		<input type="hidden" name="comment" value="NULL">
		 	</li>
			</form>
	</c:if>
	</c:forEach>
</ul>
	
<ul class="list-group">	
	<c:forEach items="${indirectNoti}" var="inotification" varStatus="loop">
	
	<c:if test="${not empty inotification.commentObj}">
			<form id="id-${loop.index}" action="/friendpost" method="post">
			<li class="list-group-item">
				<p>Your friend ${inotification.commentObj.userObj.userName} commented on post you commented</p>
		 <!--  		<p>${notification.postObj.id}</p> -->
		 		
		 		<img src="${inotification.postObj.photoURL}" onclick="submitForm('${loop.index}');" class="img-thumbnail" width="80px" height="80px"  alt="My image"/>
		 		<input type="hidden" name="Id" value="${inotification.postObj.id}"/>
		 		<input type="hidden" name="friendId" value="${inotification.postObj.userObj.userId}"/>
		 		<input type="hidden" name="comment" value="NULL">
		 	</li>
			</form>
	</c:if>	
	
	</c:forEach>
</ul>
		
		





<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" ></script>



</body>
</html>