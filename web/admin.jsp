<%-- 
    Document   : admin
    Created on : 31-May-2018, 09:00:13
    Author     : kolis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Karim Motors</title>
	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="css/app.css">
	<link rel="stylesheet" type="text/css" href="css/magic.css">
</head>
<body>
	<div class="col-md-6 col-md-offset-3" id="add-car">
		<p><?=$stat?></p>
		<h1 class="text-center"><a href="index.jsp">Karim Motors</a></h1>
		<form action="/WebAppOpe/UploadNewCar" method="post" >
			<input type="text" name="car_name" placeholder="Car Name" required />
			<textarea name="car_desc" placeholder="Car Description"></textarea>
			<label>Upload a Car Image</label>
                        <input type="file" id="image" name="image" required="true"/>
			<input type="submit" value="Add Car" class="btn-login" name="btn_add" />
		</form>
	</div>
</body>
</html>
