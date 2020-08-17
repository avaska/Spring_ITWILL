<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!-- <script src="/vc/js/plugin/load-image-orientation.js"></script> -->
<script src="/vc/js/plugin/load-image.all.min.js"></script>
<script src="https://code.jquery.com/jquery-latest.js"></script>


<title>Insert title here</title>
</head>
<body>

 <img id="img1" src="/vc/imageUpload/162C684C-F574-497C-99B0-DCADDDF218B8.jpeg" alt="" />

<style>
	#img1{width:400px;}
</style>


<script>
      var imageUrl = $("#img1").attr("src");
      alert(imageUrl);
            
      loadImage(
    		  imageUrl,
    		  function(img, data) {
    		    if (img.type === "error") {
    		      console.error("Error loading image " + imageUrl);
    		    } else {
    		      document.body.appendChild(img);
    		      
    		      var imgWidth = data.originalWidth;
    		      var imgHeight = data.originalHeight;
    		      
    		      alert(imgWidth);
    		      alert(imgHeight);
    		      
    		      var orientation = data.Oriendation;
    		      console.debug(orientation);
    		      alert(imgHeight);
    		      alert(orientation);
    		      
    		      
    		      
    		    }
    		  },
    		  { maxWidth: 600 }
    		);
      
		      
		

		
		

      
    
</script>


</body>
</html>