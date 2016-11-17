<%-- 
    Document   : home
    Created on : 07-11-2016, 12:52:56
    Author     : Jamie
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="user" scope="request" class="model.User"/>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Hjem - Sundebygninger</title>
    <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<%@ include file="navigation.jsp" %>

<div class="container-fluid">
    <h1>Velkommen til sunde bygninger!</h1>

    <c:if test="${user.role.id == 1}">
        <h2>${user.org.name}</h2>
        <div class="row">
            <div class="col-md-6">
                <h3>Buildings</h3>
                <div class="btn-group">
                    <a href="building/insert?oid=${user.org.id}"
                       class="btn btn-primary">Add Building</a>
                    <a href="buildings?oid=${user.org.id}"
                       class="btn btn-default">View Buildings</a></div>
                <div class="table-responsive">
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th>Name</th>
                            <th>Address</th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${user.org.buildings}" var="building">
                            <tr>
                                <td>
                                    <a href="building?id=${building.id}">
                                            ${building.name}</a>
                                </td>
                                <td>
                                    <a href="building?id=${building.id}">
                                            ${building.address}</a>
                                </td>
                                <td>
                                    <a href="building?id=${building.id}"
                                       class="btn btn-default">More</a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="col-md-6">
                <h3>Users</h3>
                <a href="users?oid=${user.org.id}"
                   class="btn btn-default">Vis alle</a>
                <div class="table-responsive">
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th>Name</th>
                            <th>Email</th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${user.org.users}" var="user">
                            <tr>
                                <td>
                                    <a href="customer?id=${user.id}">
                                            ${user.name}</a>
                                </td>
                                <td>
                                    <a href="customer?id=${user.id}">
                                            ${user.email}</a>
                                </td>
                                <td>
                                    <a href="customer?id=${user.id}"
                                       class="btn btn-default">More</a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="col-md-6">

            </div>
            <div class="col-md-6">

            </div>
            <div class="col-md-6">

            </div>
        </div>

    </c:if>

    <c:if test="${user.role.id == 2}">

        <div class="row">
            <div class="col-md-6">

            </div>
            <div class="col-md-6">

            </div>
            <div class="col-md-6">

            </div>
            <div class="col-md-6">

            </div>
            <div class="col-md-6">

            </div>
        </div>

    </c:if>

    <c:if test="${user.role.id == 3}">

        <div class="row">
            <div class="col-md-6">

            </div>
            <div class="col-md-6">

            </div>
            <div class="col-md-6">

            </div>
            <div class="col-md-6">

            </div>
            <div class="col-md-6">

            </div>
        </div>

    </c:if>

</div>


<script src="js/bootstrap.js" type="text/javascript"></script>
<script src="js/jquery-2.2.4.js" type="text/javascript"></script>
</body>
</html>