<%-- 
    Document   : model
    Created on : 31-May-2018, 08:58:59
    Author     : kolis
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="com.nomatech.karim.model.KarimDatabase"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean class="com.nomatech.karim.model.Cars" id="car" scope="session" > 
</jsp:useBean>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Karim Motors</title>
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/app.css">
        <link rel="stylesheet" type="text/css" href="css/magic.css">
        <style>
            body {font-family: Arial, Helvetica, sans-serif;}

            #myImg {
                border-radius: 5px;
                cursor: pointer;
                transition: 0.3s;
            }

            #myImg:hover {opacity: 0.7;}

            /* The Modal (background) */
            .modal {
                display: none; /* Hidden by default */
                position: fixed; /* Stay in place */
                z-index: 1; /* Sit on top */
                padding-top: 100px; /* Location of the box */
                left: 0;
                top: 0;
                width: 100%; /* Full width */
                height: 100%; /* Full height */
                overflow: auto; /* Enable scroll if needed */
                background-color: rgb(0,0,0); /* Fallback color */
                background-color: rgba(0,0,0,0.9); /* Black w/ opacity */
            }

            /* Modal Content (image) */
            .modal-content {
                margin: auto;
                display: block;
                width: 80%;
                max-width: 700px;
            }

            /* Caption of Modal Image */
            #caption {
                margin: auto;
                display: block;
                width: 80%;
                max-width: 700px;
                text-align: center;
                color: #ccc;
                padding: 10px 0;
                height: 150px;
            }

            /* Add Animation */
            .modal-content, #caption {    
                -webkit-animation-name: zoom;
                -webkit-animation-duration: 0.6s;
                animation-name: zoom;
                animation-duration: 0.6s;
            }

            @-webkit-keyframes zoom {
                from {-webkit-transform:scale(0)} 
                to {-webkit-transform:scale(1)}
            }

            @keyframes zoom {
                from {transform:scale(0)} 
                to {transform:scale(1)}
            }

            /* The Close Button */
            .close {
                position: absolute;
                top: 75px;
                right: 25px;
                color: #f5f5f5;
                font-size: 40px;
                font-weight: bold;
                transition: 0.3s;
            }

            .close:hover,
            .close:focus {
                color: #fff;
                text-decoration: none;
                cursor: pointer;
            }

            /* 100% Image Width on Smaller Screens */
            @media only screen and (max-width: 700px){
                .modal-content {
                    width: 100%;
                }
            }
        </style>

    </head>
    <body>
        <nav class="nav navbar-fixed-top">
            <div class="container-fluid" id="header-title">
                <div class="navbar-header">
                    <a class="navbar-brand" href="userPage.jsp">Back to Karim Collection</a>
                </div>
            </div>
        </nav>
        <div class="row depts">
            <div class="row">
                <div class="col-sm-offset-1 col-sm-4">
                    <div class="thumbnail parts">

                        <%
                            if (car.getCarId() != 0) {
                                String query = " select * from cars_tb where car_id = " + car.getCarId();
                                KarimDatabase karim = new KarimDatabase();
                                Statement stm = karim.getCon().createStatement();
                                ResultSet rs = stm.executeQuery(query);
                                rs.next();
                                car.setCarDesc(rs.getString("car_desc"));
                                car.setCarName(rs.getString("car_name"));
                                car.setCarImg(rs.getString("car_img"));
                                karim.getCon().close();
                                car.createTestData(car.getCarId());
                            }
                        %> 
                        <img id="myImg" src= ${car.carImg} alt= ${car.carName} height="500" /> 
                    </div>
                      <div class="col-sm-2">
                        <h1>Percent Recommendation </h1>
                        <h3>Neutral: <p> 20</p></h3>
                        <h3 style="color: green">Positive: <p> 39 </p></h3>
                        <h3 style="color: red">Negative: <p> 21</p></h3> 
                    </div>
                </div>
                    

                <div class="col-sm-6">
                    <div class="thumbnail parts" style="padding: 5px 15px;">
                        <h1> ${car.carName}</h1>
                        <h5>${car.carDesc}</h5><br>
                        <form action="/WebAppOpe/UploadComments" method="post">
                            <textarea name="comment" placeholder="Comment" column="10"></textarea>
                            <input type="submit" value="Post Comment" class="btn-login" name="btn_post" />
                            <input type ="hidden" name="carIdComment" value="${car.carId}" />
                        </form>
                    </div>
                </div>
                      
                      
            </div>
            <div class="row">
                <div class="col-sm-offset-2 col-sm-8">
                    <div class="thumbnail parts" style="padding: 5px 15px;">
                        <h3 class="text-center">Previous Comments</h3>
                        <%
                            if (car.getCarId() != 0) {
                                String getComments = "select * from comments_tb where car_id = "
                                        + car.getCarId() + " order by time_stamp DESC";
                                KarimDatabase karim2 = new KarimDatabase();
                                Statement stm2 = karim2.getCon().createStatement();
                                ResultSet rs2 = stm2.executeQuery(getComments);
                                while (rs2.next()) {
                                    out.println("<p>" + rs2.getString(4) + "</p>");
                                    out.println("<h4>" + rs2.getString(3) + "</h4>");
                                }
                                karim2.getCon().close();
                            }
                        %>
                    </div>
                </div>
            </div>
        </div>
        <footer>
            <div class="container-fluid">
                <p class="text-center">Designed by Opeyemi Makinde</p>
            </div>
        </footer>
        <!-- The Modal -->
        <div id="myModal" class="modal">
            <span class="close">&times;</span>
            <img class="modal-content" id="img01">
            <div id="caption"></div>
        </div>

        <script>
            // Get the modal
            var modal = document.getElementById('myModal');

            // Get the image and insert it inside the modal - use its "alt" text as a caption
            var img = document.getElementById('myImg');
            var modalImg = document.getElementById("img01");
            var captionText = document.getElementById("caption");
            img.onclick = function () {
                modal.style.display = "block";
                modalImg.src = this.src;
                captionText.innerHTML = this.alt;
            }

            // Get the <span> element that closes the modal
            var span = document.getElementsByClassName("close")[0];

            // When the user clicks on <span> (x), close the modal
            span.onclick = function () {
                modal.style.display = "none";
            }
        </script>

    </body>
</html>

