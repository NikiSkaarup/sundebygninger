<%-- 
    Document   : ViewAllCustomers
    Created on : 02-11-2016, 12:13:31
    Author     : Tanja
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="Users" scope="request" type="java.util.List<model.User>"/>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>

        <title>List for all customers</title>
    </head>
    <body>
        <%@include file="navigation.jsp"  %>
        <h3>Liste over alle kunder</h3>
        <form form action="AllCustomers" method="POST" class="form-horizontal" enctype="multipart/form-data">
            <table class="active">
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Email</th>
                        <th>Link</th>
                    </tr>  
                </thead>
                <tbody>
                    <c:forEach items="${Users}" var="u">
                    <tr>
                        <td>${u.Name}</td>
                        <td>${u.Email}</td>
                        <td><a href="user?id=${u.id}">Info</a> </td>
                    </tr>
                    </c:forEach>
                </tbody>
            </table>
            <button type="submit" class="btn btn-primary">Tilføj en ny kunde</button>
        </form>
        <script src="js/jquery-2.2.4.js" type="text/javascript"></script>
        <script src="js/bootstrap.js" type="text/javascript"></script>
    </body>
</html>
