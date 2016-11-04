<%--
  Created by IntelliJ IDEA.
  User: Niki
  Date: 2016-11-04
  Time: 11:27 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="requests" scope="request" type="java.util.List"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table>
    <tr>
        <th>Name</th>
        <th>Address</th>
        <th>Link</th>
    </tr>
    <c:forEach items="${requests}" var="r">
        <tr>
            <td>${r.name}</td>
            <td>${r.description}</td>
            <td>${r.id}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
