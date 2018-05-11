<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" >
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<title>Instapic</title>

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
 <!--   <a class="btn btn-outline-success my-2 my-sm-0 link " href="/myprofile">My Profile</a> -->
  
   <a  href="/notification"> <i class="fa fa-bell" style="font-size:36px" ></i></a>&nbsp;&nbsp;&nbsp;&nbsp;
 <a  href="/myfriends"> <i class="fas fa-user-friends" style="font-size:36px" ></i></a>&nbsp;&nbsp;&nbsp;&nbsp;
 <a  href="/editprofile"><i class="far fa-edit" style="font-size:36px"  ></i></a>&nbsp;&nbsp;&nbsp;&nbsp;
 <a  href="/postcreation"><i class="fa fa-video-camera" style="font-size:36px" ></i></a>&nbsp;&nbsp;&nbsp;&nbsp;
	<a href="/myprofile"> <i class="fas fa-home"  style="font-size:36px" ></i></a>

 
  	
  </form>
  
  <fb:login-button scope="public_profile,email,user_friends" data-max-rows="1" data-size="large" data-button-type="continue_with" data-show-faces="false" data-auto-logout-link="true" data-use-continue-as="false"
onlogin="checkLoginState();">
</fb:login-button>
  
</nav>
<br>
<br>

<script>
  // This is called with the results from from FB.getLoginStatus().
  function statusChangeCallback(response) {
    console.log('statusChangeCallback');
    console.log(response);
    // The response object is returned with a status field that lets the
    // app know the current login status of the person.
    // Full docs on the response object can be found in the documentation
    // for FB.getLoginStatus().
    if (response.status === 'connected') {
      // Logged into your app and Facebook.
     // testAPI();
    } else {
    	window.location = '/logout';
      // The person is not logged into your app or we are unable to tell.
      document.getElementById('status').innerHTML = 'Please log ' +
        'into this app.';
      console.log("before logout");
      
    }
  }

  // This function is called when someone finishes with the Login
  // Button.  See the onlogin handler attached to it in the sample
  // code below.
  function checkLoginState() {
	  console.log("calling checkloginstate")
    FB.getLoginStatus(function(response) {
     statusChangeCallback(response);
    });
  }

  window.fbAsyncInit = function() {
    FB.init({
      appId      : '274804616394811',
      cookie     : true,  // enable cookies to allow the server to access 
                          // the session
      xfbml      : true,  // parse social plugins on this page
      version    : 'v2.8' // use graph api version 2.8
    });

    // Now that we've initialized the JavaScript SDK, we call 
    // FB.getLoginStatus().  This function gets the state of the
    // person visiting this page and can return one of three states to
    // the callback you provide.  They can be:
    //
    // 1. Logged into your app ('connected')
    // 2. Logged into Facebook, but not your app ('not_authorized')
    // 3. Not logged into Facebook and can't tell if they are logged into
    //    your app or not.
    //
    // These three cases are handled in the callback function.

   // FB.getLoginStatus(function(response) {
   //   statusChangeCallback(response);
   // });

  };

  // Load the SDK asynchronously
  (function(d, s, id) {
    var js, fjs = d.getElementsByTagName(s)[0];
    if (d.getElementById(id)) return;
    js = d.createElement(s); js.id = id;
    js.src = "https://connect.facebook.net/en_US/sdk.js";
    fjs.parentNode.insertBefore(js, fjs);
  }(document, 'script', 'facebook-jssdk'));

  // Here we run a very simple test of the Graph API after login is
  // successful.  See statusChangeCallback() for when this call is made.
  </script>

<!--
  Below we include the Login Button social plugin. This button uses
  the JavaScript SDK to present a graphical Login button that triggers
  the FB.login() function when clicked.

 
<fb:login-button scope="public_profile,email,user_friends" data-max-rows="1" data-size="large" data-button-type="continue_with" data-show-faces="false" data-auto-logout-link="true" data-use-continue-as="false"
onlogin="checkLoginState();">
</fb:login-button> -->





<script>
	function submitForm(index){
		var form = document.getElementById("id-"+index);
		form.submit()
	}
</script>

<h1 class="display-6">Your Friends</h1>
<div class="container p-3"></div>
<c:if test="${empty userFriend}">
<h6>None of your friends are registered to INSTAPIC yet!</h6></c:if>

<c:if test="${not empty userFriend}">
<ul class="list-group">
	<c:forEach items="${userFriend}" var="userFriend" varStatus="loop">
	<form id="id-${loop.index}" action="/friendprofile" method="post">
		<li class="list-group-item">
			<img src="${userFriend.profilePhoto}" class="img-thumbnail" width="80px" height="80px" 
			onclick="submitForm('${loop.index}');"  alt="My image"/>
			<c:out value="${userFriend.userName}"/>
			<input type="hidden" name="friendId" value="${userFriend.userId}"/>
		</li>
	</form>
	</c:forEach>
 </ul>
 </c:if>
 
 <c:forEach items="${userFriend}" var="userFriend" varStatus="loop">
 	<form object="${userFriend}" action="@{/friendsearch}" method="get">
 		<input type="text" name="search" id="search" value="${search}"/>
        <input type="submit" value="Search"/>
 	</form>
 </c:forEach>

	

</body>
</html>