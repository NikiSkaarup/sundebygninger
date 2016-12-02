<%-- 
    Document   : allOrgs
    Created on : 29-11-2016, 11:48:42
    Author     : Jamie
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="orgs" scope="request" type="java.util.List<model.Org>"/>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Organisationer</title>
        <link href="<c:url value="/css/bootstrap.css"/>" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <%@include file="navigation.jsp"  %>
        <div class="container-fluid">
            <h1>Alle organisationer</h1>
            <div class="table-responsive">
                <table class="table table-bordered table-hover table-striped">
                    <thead>
                        <tr class="active">
                            <th>Navn</th>
                            <th>Telefon Nr.</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${orgs}" var="o">
                            <tr>
                                <td>${o.name}</td>
                                <td>${o.phone}</td>
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