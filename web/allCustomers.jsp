<%-- 
    Document   : ViewAllCustomers
    Created on : 02-11-2016, 12:13:31
    Author     : Tanja
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="customers" scope="request" type="java.util.List<model.User>"/>
<jsp:useBean id="org" scope="request" class="model.Org"/>
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
        <h3>Liste over alle kunder</h3>

        <table class="table table-bordered table-hover table-striped">
            <thead>
                <tr class="active">
                    <th>Navn</th>
                    <th>Email</th>
                    <th>Link</th>
                </tr>  
            </thead>
            <tbody>
                <c:forEach items="${customers}" var="u">
                    <tr>
                        <td>${u.name}</td>
                        <td>${u.email}</td>
                        <td><a href="customer?id=${u.id}">Mere</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <c:if test="${org.id > 0}">
            <a href="customer/insert?orgid=<c:out value="${org.id}"/>" class="btn btn-primary">Tilføj en ny kunde</a>
        </c:if>
        <script src="js/jquery-2.2.4.js" type="text/javascript"></script>
        <script src="js/bootstrap.js" type="text/javascript"></script>
    </body>
</html>
