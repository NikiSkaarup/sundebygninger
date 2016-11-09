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

        <title>List for one customer</title>
    </head>
    <body>
        <%@include file="navigation.jsp"  %>
        <h4>Oplysninger om den pågældende kunde</h4>
        <table border="" class="col-md-4">
    </body>


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
        <a href="addUpdateCustomer.jsp">Redigere kunde</a>
    </div>

</body>
</table>
<script src="js/jquery-2.2.4.js" type="text/javascript"></script>
<script src="js/bootstrap.js" type="text/javascript"></script>
</div>
</body>
</html>
