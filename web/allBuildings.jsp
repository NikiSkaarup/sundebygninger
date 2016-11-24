<%-- 
    Document   : Allbuildings
    Created on : 27-10-2016, 22:41:55
    Author     : Menja
--%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="buildings" scope="request" type="java.util.List<model.Building>"/>
<jsp:useBean id="org" scope="request" class="model.Org"/>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="<c:url value="/css/bootstrap.css"/>" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <%@include file="navigation.jsp"  %>
        <div class="container-fluid">
            <a href="building/insert?orgid=<c:out value="${org.id}"/>" class="btn btn-primary">Add building</a>
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
                                <td><a href="building?id=${b.id}">Mere</a></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <script src="<c:url value="/js/bootstrap.js"/>" type="text/javascript"></script>
        <script src="<c:url value="/js/jquery-2.2.4.js"/>"
        type="text/javascript"></script>
    </body>
</html>
