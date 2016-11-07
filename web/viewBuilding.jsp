<%-- 
    Document   : viewBuilding
    Created on : 02-11-2016, 13:45:34
    Author     : Menja
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean beanName="buildingBean" id="building" scope="request" type="model.Building"/>
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

                <p>Organisation: ${building.org}</p>
                <p>Navn på bygning: ${building.name}</p>
                <p>Adresse: ${building.address}</p>
                <p>Byggeår: ${building.constructionYear}</p>
                <p>Nuværende benyttelse: ${building.currentUse}</p>
                <p>Areal: ${building.area}</p>
                <p>Tidlige benyttelse: ${building.previousUse}</p>

                <a href="image?b=${building.id}">Tilføj billeder</a>
                <a href="document?b=${building.id}">Tilføj dokumenter/rapporter</a>
                <a href="addUpdateBuilding.jsp?id=">Rediger bygning</a>
            </div>
        </div>
        <script src="js/jquery-2.2.4.js" type="text/javascript"></script>
        <script src="js/bootstrap.js" type="text/javascript"></script>
    </body>
</html>
