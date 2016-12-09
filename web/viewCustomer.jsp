<%-- 
    Document   : viewCustomer
    Created on : 03-11-2016, 09:19:11
    Author     : Tanja
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="u" scope="request" class="model.User"/>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="navigation.jsp"%>
        
        <h4>Info om den enkelte kunde</h4>
        <table border="" class="col-md-4">

            <tr>
                <th>ID</th>
                <td>${u.id}</td>
            </tr>
            <tr>
                <th>Navn</th>
                <td>${u.name}</td>
            </tr>
            <tr>
                <th>Email</th>
                <td>${u.email}</td>
            </tr>
            <tr>
                <th>Tlf.nummer</th>
                <td>${u.phone}</td>
            </tr>
            
            <div>
                <a href="/customer/update?id=${u.id}">Redigér kunde</a>
            </div>

    </body>
</table>
        <script src="js/jquery-2.2.4.js" type="text/javascript"></script>
        <script src="js/bootstrap.js" type="text/javascript"></script>

 </body>
</html>
