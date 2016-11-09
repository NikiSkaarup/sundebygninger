<%-- 
    Document   : viewBuilding
    Created on : 02-11-2016, 13:45:34
    Author     : Menja
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="b" scope="request" class="model.Building"/>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <%@include file="navigation.jsp" %>

        <div class="container-fluid">
            <div class="row">
                <h1>Building</h1>

                <p>Organisation: ${b.org.id}</p>
                <p>Navn på bygning: ${b.name}</p>
                <p>Adresse: ${b.address}</p>
                <p>Byggeår: ${b.constructionYear}</p>
                <p>Nuværende benyttelse: ${b.currentUse}</p>
                <p>Areal: ${b.area}</p>
                <p>Tidlige benyttelse: ${b.previousUse}</p>

                <a href="image?b=${b.id}">Tilføj billeder</a>
                <a href="document?b=${b.id}">Tilføj dokumenter/rapporter</a>
                <a href="building?id=${b.id}">Rediger bygning</a>
            </div>
        </div>
        <script src="js/jquery-2.2.4.js" type="text/javascript"></script>
        <script src="js/bootstrap.js" type="text/javascript"></script>
    </body>
</html>
