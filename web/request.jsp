<%--
  Created by IntelliJ IDEA.
  User: Niki
  Date: 2016-11-04
  Time: 11:27 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="requests" scope="request" type="java.util.List<model.Request>"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Request - Sundebygninger</title>
    <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<%@ include file="navigation.jsp" %>

<div class="container">
    <div class="table-responsive">
        <table class="table table-bordered">
            <tr>
                <th>id</th>
                <th>Description</th>
                <th>ServiceType</th>
                <th>Links</th>
            </tr>
            <c:forEach items="${requests}" var="r">
                <tr>
                    <td>${r.id}</td>
                    <td>${r.description}</td>
                    <td>${r.serviceType.name}</td>
                    <td>${r.id}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

<script src="js/jquery-2.2.4.js" type="text/javascript"></script>
<script src="js/bootstrap.js" type="text/javascript"></script>
</body>
</html>
