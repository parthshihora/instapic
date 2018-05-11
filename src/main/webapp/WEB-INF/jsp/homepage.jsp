<html>
<head>
<title>Welcome to Instapic</title>
<meta charset="UTF-8">

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" >
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>

<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" ></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" ></script>

		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
		
		<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.12/css/all.css" integrity="sha384-G0fIWCsCzJIMAVNQPfjH08cyYaUtMwjJwqiRKxxE/rx96Uroj1BtIQ6MLJuheaO9" crossorigin="anonymous">




</head>
<body>

 <nav class="navbar navbar-dark bg-dark" >
  <a class="navbar-brand" href="#">
    <img src="logo.jpeg" width="30" height="30" class="d-inline-block align-top" alt="">
    INSTAPIC
  </a>
  <form class="form-inline my-2 my-lg-0">
 <!--   <a class="btn btn-outline-success my-2 my-sm-0 link " href="/myfriends">View Friends</a>
  <a class="btn btn-outline-success my-2 my-sm-0 link " href="/editprofile">Edit Profile</a>
   <a class="btn btn-outline-success my-2 my-sm-0 link " href="/postcreation">Post</a>
    <a class="btn btn-outline-success my-2 my-sm-0 link " href="/notification">Notification</a>
    -->
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
      testAPI();
    } else {
 //    	window.location = '/logout';
      // The person is not logged into your app or we are unable to tell.
      document.getElementById('status').innerHTML = 'Please log ' +
        'into this app.';
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
  console.log("before run");
  var hasRun = false;
  function testAPI() {
	  if(hasRun == false){
		  hasRun = true;
    console.log('Welcome!  Fetching your information.... ');
    FB.api('/me?fields=id,name,email', function(response) {
      console.log('Successful login for: ' + response.name);
      document.getElementById('status').innerHTML =
        'Thanks for logging in, ' + response.name + '!';
      $('[name="myId"]').val(response.id);
      $('[name="myName"]').val(response.name);
      $('[name="myEmail"]').val(response.email);
      
      
      FB.api('/me/friends', function(response){
    		console.log(response);
    		
  			response.data.forEach(function(ele,i){
        		
        		console.log(i);
        		console.log(ele.name);
        	});
        		var jsonObj = JSON.stringify(response.data)
            	$('[name="myFriends"]').val(jsonObj);
        		console.log("before submitting form..........");
            	$("#redirectFormID").submit();
            	
        	});
      
    });
	  }		
  }
</script>

<!--
  Below we include the Login Button social plugin. This button uses
  the JavaScript SDK to present a graphical Login button that triggers
  the FB.login() function when clicked.

 
<fb:login-button scope="public_profile,email,user_friends" data-max-rows="1" data-size="large" data-button-type="continue_with" data-show-faces="false" data-auto-logout-link="true" data-use-continue-as="false"
onlogin="checkLoginState();">
</fb:login-button>  -->

<div id="status">
</div>


<div class="container">
<div class="row">
		<form id="redirectFormID" method="POST" action="redirectForm">
		<input type="hidden" name="myId">
		<input type="hidden" name="myName">
		<input type="hidden" name="myEmail">
		<input type="hidden" name="myFriends">

		</form>
	</div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" ></script>
	
	<h1 class="heading">Welcome to INSTAPIC</h1>
	
	


</body>
</html>