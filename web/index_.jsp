<%-- 
    Document   : index
    Created on : 01-Jun-2018, 11:03:49
    Author     : kolis
--%>

<%@page import="java.util.logging.Logger"%>
<%@page import="java.util.logging.Level"%>
<%@page import="java.util.logging.Level"%>f
<%@page import="java.sql.SQLException"%>
<%@page import="com.nomatech.karim.model.Cars"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.nomatech.karim.model.KarimDatabase" %>
<!DOCTYPE html>
<html>
    <head>
        <jsp:useBean class="com.nomatech.karim.model.Cars" id="car" scope="session" > 

        </jsp:useBean>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Karim Motors</title>
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/app.css">
        <link rel="stylesheet" type="text/css" href="css/magic.css">
    </head>
    <body>
        <nav class="nav navbar-fixed-top">
            <div class="container-fluid" id="header-title">
                <div class="navbar-header">
                    <a class="navbar-brand" href="admin.jsp">Welcome to Karim Motors</a>
                    <a  class="navbar-brand" href="admin.jsp">Upload new car</a>
                </div> 
               
            </div>
        </nav>
        <div class="row depts">
            <div class="row">
                
                
                <%                    
                    car.setCars();
                    for (int i = 0; i < car.getTotalNumberOfCars(); i++) {
                       out.println("<a  class=\"car-link\" href=\"/WebAppOpe/setCarId?id="+ car.getCars()[i].getCarId()+"\"id"+ "=\"" +car.getCars()[i].getCarId() + "\">");
                                              out.println("<div class=\"col-sm-6 col-md-3\"> ");
                       out.println("<div class=\"thumbnail parts\">");
                       out.println("<img src=\""+ car.getCars()[i].getCarImg()+ "\" alt=\"...\""+ " height=\"300\">");
                       out.println("<div class=\"caption\"");
                       out.println("<h1>"+car.getCars()[i].getCarName()+"</h1>");
                       out.println("<p>"+car.getCars()[i].getCarDesc()+"</p>");
                       out.println("</div>");
                       out.println("</div>");
                       out.println("</a>");
                       out.println("</div>");
                       
                    }
                    
                %>
                




        </div>
        <footer>
            <div class="container-fluid">
                <p class="text-center">Designed by Opeyemi Makinde</p>
            </div>
        </footer>
    </body>
</html>

