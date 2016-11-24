<%-- 
    Document   : home
    Created on : 07-11-2016, 12:52:56
    Author     : Jamie
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="home" scope="request" class="view.model.Home"/>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Hjem - Sundebygninger</title>
    <link href="<c:url value="/css/bootstrap.css"/>" rel="stylesheet" type="text/css"/>
</head>
<body>
<%@ include file="navigation.jsp" %>

<div class="container-fluid">
    <c:if test="${home.role.id == 1}">
        <%-- Customer --%>

        <h2>${home.org.name}</h2>
        <div class="row">
            <div class="col-md-6">
                    <%-- Display buildings --%>
                <h3>Buildings</h3>
                <div class="btn-group">
                    <a href="building/insert?orgid=${home.org.id}"
                       class="btn btn-primary">Tilføj</a>
                    <a href="buildings?orgid=${home.org.id}"
                       class="btn btn-default">Vis Alle</a></div>
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
                        <c:forEach items="${home.buildings}" var="b">
                            <tr>
                                <td>
                                    <a href="building?id=${b.id}">
                                            ${b.name}</a>
                                </td>
                                <td>
                                    <a href="building?id=${b.id}">
                                            ${b.address}</a>
                                </td>
                                <td>
                                    <a href="building?id=${b.id}"
                                       class="btn btn-default">More</a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="col-md-6">
                    <%-- Display users --%>
                <h3>Customers</h3>
                <a href="customers?oid=${home.org.id}"
                   class="btn btn-default">Vis Alle</a>
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
                        <c:forEach items="${home.users}" var="u">
                            <tr>
                                <td>
                                    <a href="customer?id=${u.id}">
                                            ${u.name}</a>
                                </td>
                                <td>
                                    <a href="customer?id=${u.id}">
                                            ${u.email}</a>
                                </td>
                                <td>
                                    <a href="customer?id=${u.id}"
                                       class="btn btn-default">More</a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="col-md-6">
                    <%-- Display requests --%>

            </div>
            <div class="col-md-6">
                    <%-- Display reports --%>

            </div>
            <div class="col-md-6">
                    <%-- Display incidents --%>

            </div>
        </div>

    </c:if>

    <c:if test="${home.role.id == 2}">
        <%-- Employee --%>

        <div class="row">
            <div class="col-md-6">
                    <%-- Display unaccepted requests --%>

            </div>
            <div class="col-md-6">
                    <%-- Display accepted requests --%>

            </div>
            <div class="col-md-6">
                    <%-- Display reports --%>

            </div>
        </div>

    </c:if>

    <c:if test="${home.role.id == 3}">
        <%-- Admin --%>

        <div class="row">
            <div class="col-md-6">
                    <%-- Display organizations --%>

            </div>
            <div class="col-md-6">
                    <%-- Display users --%>
                <h3>Users</h3>
                <a href="customers" class="btn btn-default">Vis Alle</a>
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
                        <c:forEach items="${home.users}" var="u">
                            <tr>
                                <td>
                                    <a href="customer?id=${u.id}">
                                            ${u.name}</a>
                                </td>
                                <td>
                                    <a href="customer?id=${u.id}">
                                            ${u.email}</a>
                                </td>
                                <td>
                                    <a href="customer?id=${u.id}"
                                       class="btn btn-default">More</a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="col-md-6">
                    <%-- Display buildings --%>
                <h3>Buildings</h3>
                <div class="btn-group">
                    <a href="buildings" class="btn btn-default">Vis Alle</a>
                </div>
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
                        <c:forEach items="${home.buildings}" var="b">
                            <tr>
                                <td>
                                    <a href="building?id=${b.id}">
                                            ${b.name}</a>
                                </td>
                                <td>
                                    <a href="building?id=${b.id}">
                                            ${b.address}</a>
                                </td>
                                <td>
                                    <a href="building?id=${b.id}"
                                       class="btn btn-default">More</a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="col-md-6">
                    <%-- Display requests --%>

            </div>
            <div class="col-md-6">
                    <%-- Display reports --%>

            </div>
        </div>

    </c:if>

</div>

<script src="<c:url value="/js/bootstrap.js"/>" type="text/javascript"></script>
<script src="<c:url value="/js/jquery-2.2.4.js"/>" type="text/javascript"></script>
</body>
</html>