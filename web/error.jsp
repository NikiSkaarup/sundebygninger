<%--
  Created by IntelliJ IDEA.
  User: Niki
  Date: 2016-11-10
  Time: 11:13 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error - Sundebygninger</title>
    <link href="/css/bootstrap.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<%@ include file="navigation.jsp" %>

<div class="container-fluid">

    <h1>Error Page</h1>
    <p>${requestScope.error}</p>
</div>

<script src="/js/bootstrap.js" type="text/javascript"></script>
<script src="/js/jquery-2.2.4.js" type="text/javascript"></script>

</body>
</html>