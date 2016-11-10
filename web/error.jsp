<%--
  Created by IntelliJ IDEA.
  User: Niki
  Date: 2016-11-10
  Time: 11:13 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="error" scope="request" type="java.lang.String" />
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error - Sundebygninger</title>
    <link href="<c:url value="/css/bootstrap.css"/>"
          rel="stylesheet" type="text/css"/>
</head>
<body>
<%@ include file="navigation.jsp" %>

<div class="container-fluid">

    <h1>Error Page</h1>
    <p>${error}</p>
</div>

<script src="<c:url value="/js/bootstrap.js"/>" type="text/javascript"></script>
<script src="<c:url value="/js/jquery-2.2.4.js"/>"
        type="text/javascript"></script>

</body>
</html>