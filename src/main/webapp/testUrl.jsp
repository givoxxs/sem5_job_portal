<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Upload Image to Cloudinary</title>
</head>

<body>
    <h1>Upload Image to Cloudinary</h1>
    <form id="uploadForm" action="upload" method="post" enctype="multipart/form-data">
        <input type="file" id="fileInput" name="image" required />
        <button type="submit">Upload</button>
    </form>
    <div id="result">
    	<p>${imageUrl}<p>
    	<img src="${imageUrl }" alt="test">
    </div>
    </body>

</html>