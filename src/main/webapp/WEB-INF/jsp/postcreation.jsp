<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
  <meta charset="utf-8">
  <title>Audio-only Example - Record Plugin for Video.js</title>
	<link href="https://vjs.zencdn.net/6.6.3/video-js.css" rel="stylesheet">
<link href="https://cdnjs.cloudflare.com/ajax/libs/videojs-record/2.1.0/css/videojs.record.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/video.js/6.7.2/video.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/RecordRTC/5.4.6/RecordRTC.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/adapterjs/0.15.0/adapter.min.js"></script>
<script src="https://collab-project.github.io/videojs-record/dist/wavesurfer.min.js"></script>
<script src="https://collab-project.github.io/videojs-record/dist/wavesurfer.microphone.min.js"></script>
<script src="https://collab-project.github.io/videojs-record/dist/videojs.wavesurfer.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/videojs-record/2.1.2/videojs.record.min.js"></script>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" >
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>

  <style>
  /* change player background color */
  #myAudio {
      background-color: #9FD6BA;
  }
  </style>
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






<label for="InputFile">Record Audio: </label>
<audio id="myAudio" class="video-js vjs-default-skin"></audio>
<label for="InputFile">Capture Image:</label>
<video id="myImage" class="video-js vjs-default-skin"></video>


<script>
var player = videojs("myAudio", {
    controls: true,
    width: 600,
    height: 300,
    plugins: {
        wavesurfer: {
            src: "live",
            waveColor: "#36393b",
            progressColor: "black",
            debug: true,
            cursorWidth: 1,
            msDisplayMax: 20,
            hideScrollbar: true
        },
        record: {
            audio: true,
            video: false,
            maxLength: 20,
            debug: true
        }
    }
}, function(){
    // print version information at startup
    videojs.log('Using video.js', videojs.VERSION,
        'with videojs-record', videojs.getPluginVersion('record'),
        '+ videojs-wavesurfer', videojs.getPluginVersion('wavesurfer'),
        'and recordrtc', RecordRTC.version);
});

// error handling
player.on('deviceError', function() {
    console.log('device error:', player.deviceErrorCode);
});

// user clicked the record button and started recording
player.on('startRecord', function() {
    console.log('started recording!');
});

// user completed recording and stream is available
player.on('finishRecord', function() {
    // the blob object contains the recorded data that
    // can be downloaded by the user, stored on server etc.
    console.log('finished recording: ', player.recordedData);
   var reader = new FileReader();
   var base64data;
   reader.readAsDataURL(player.recordedData);
    reader.onloadend = function(){
    	base64data = reader.result;
    	console.log("base64data",base64data);
    $("#recording").val(base64data);	
    }
    
});


var implayer = videojs("myImage", {
    controls: true,
    width: 320,
    height: 240,
    controlBar: {
        volumePanel: false,
        fullscreenToggle: false
    },
    plugins: {
        record: {
            image: true,
            debug: true
        }
    }
}, function(){
    // print version information at startup
    videojs.log('Using video.js', videojs.VERSION,
        'with videojs-record', videojs.getPluginVersion('record'));
});

// error handling
implayer.on('deviceError', function() {
    console.warn('device error:', implayer.deviceErrorCode);
});

// snapshot is available
implayer.on('finishRecord', function() {
    // the blob object contains the image data that
    // can be downloaded by the user, stored on server etc.
    console.log('snapshot ready: ', implayer.recordedData);
	//console.log("base64data",imbase64data);
	var imbase64data = implayer.recordedData;
    $("#capture").val(imbase64data);	
  //  var imreader = new FileReader();
  //  var imbase64data = implayer.recordedData;
  //  imreader.readAsDataURL(implayer.recordedData);
 //    imreader.onloadend = function(){
  //  	imbase64data = imreader.result;
   //  	console.log("base64data",imbase64data);
    // $("#capture").val(imbase64data);	
    // }
     
}); 

$(document).ready(function(){
	$("#savebutton").on("click",function(){
		$("#audioForm").submit();
	});
});
</script>


<div class="container">
		<div class="row">
<form id="audioForm" action="/base64Audio" method="post" enctype="multipart/form-data">
	<div class="form-group">
		
		<input type="hidden" id="recording" name="recording">
	</div>
	<div class="form-group">
		
		<input type="hidden" id="capture" name="capture">
	</div>
<!--  	<div class="form-group">
		<label for="InputFile">Upload Profile Picture: </label>
		<input type="file" class="form-control" name="file"/>
	</div> -->
	<div class="form-group">
		<label for="aboutPhoto">About photo</label> 
		<input type="text" name="aboutPhoto" class="form-control" id="aboutPhoto"
		aria-describedby="desciptionHelp" placeholder="Abour Photo"> 
	</div>
	<button type="submit" id="savebutton" class="btn btn-primary">Post</button>
</form>
</div>
</div>
	
</body>
</html>
