<%-- 
    Document   : Allbuildings
    Created on : 27-10-2016, 22:41:55
    Author     : Menja
--%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean beanName="buildingsBean" id="buildings" scope="request" type="java.util.List"/>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <%@include file="navigation.jsp"  %>
        <div class="container-fluid">
            <h1>All Buildings</h1>
            <div class="table-responsive">
                <table class="table table-bordered table-hover table-striped">
                    <thead>
                        <tr class="active">
                            <th>Name</th>
                            <th>Address</th>
                            <th>Link</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${buildings}" var="b">
                            <tr>
                                <td>${b.name}</td>
                                <td>${b.address}</td>
                                <td>${b.id}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <script src="js/jquery-2.2.4.js" type="text/javascript"></script>
        <script src="js/bootstrap.js" type="text/javascript"></script>
    </body>
</html>
