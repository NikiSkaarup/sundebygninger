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
    <title>Home - Sundebygninger</title>
    <link href="<c:url value="/css/bootstrap.css"/>" rel="stylesheet"
          type="text/css"/>
</head>
<body>
<%@ include file="navigation.jsp" %>

<div class="container-fluid">

    <c:if test="${home.user.role.id == 1}">
        <%-- Customer --%>
        <h2>${home.user.org.name}</h2>
        <div class="row">
            <c:if test="${home.buildings.size() > 0}">
                <div class="col-md-6">
                        <%-- Display buildings --%>
                    <h3>Bygninger</h3>
                    <div class="btn-group">
                        <a href="building/insert?orgid=${home.user.org.id}"
                           class="btn btn-primary">Tilføj</a>
                        <a href="buildings?orgid=${home.user.org.id}"
                           class="btn btn-default">Vis Alle</a></div>
                    <div class="table-responsive">
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th>Navn</th>
                                <th>Adresse</th>
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
                                        <a href="building?id=${b.id}">Mere</a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </c:if>
            <c:if test="${home.users.size() > 0}">
                <div class="col-md-6">
                        <%-- Display users --%>
                    <h3>Kunder</h3>
                    <a href="customers?oid=${home.user.org.id}"
                       class="btn btn-default">Vis Alle</a>
                    <div class="table-responsive">
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th>Navn</th>
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
                                        <a href="customer?id=${u.id}">Mere</a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </c:if>
            <c:if test="${home.requests.size() > 0}">
                <div class="col-md-6">
                        <%-- Display requests --%>
                    <h3>Forespørgsel</h3>
                    <a href="requests?oid=${home.user.org.id}"
                       class="btn btn-default">Vis Alle</a>
                    <div class="table-responsive">
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th>Service Type</th>
                                <th>Beskrivelse</th>
                                <th></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${home.requests}" var="r">
                                <tr>
                                    <td>${r.serviceType.name}</td>
                                    <td>${r.description}</td>
                                    <td>
                                        <a href="request?id=${r.id}">Mere</a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </c:if>
            <c:if test="${home.reports.size() > 0}">
                <div class="col-md-6">
                        <%-- Display reports --%>
                    <h3>Reporter</h3>
                    <a href="reports?oid=${home.user.org.id}"
                       class="btn btn-default">Vis Alle</a>
                    <div class="table-responsive">
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th>Bygnings navn</th>
                                <th>Tilføjelse</th>
                                <th></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${home.reports}" var="r">
                                <tr>
                                    <td>
                                        <a href="report?id=${r.id}">
                                                ${r.building.name}</a>
                                    </td>
                                    <td>
                                        <a href="report?id=${r.id}">
                                                ${r.submission.toGMTString()}</a>
                                    </td>
                                    <td>
                                        <a href="report?id=${r.id}">Mere</a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </c:if>
            <c:if test="${home.incidents.size() > 0}">
                <div class="col-md-6">
                        <%-- Display incidents --%>
                    <h3>Skader</h3>
                    <a href="incidents?oid=${home.user.org.id}"
                       class="btn btn-default">Vis Alle</a>
                    <div class="table-responsive">
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th>Navn</th>
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
                                        <a href="customer?id=${u.id}">Mere</a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </c:if>
        </div>
    </c:if>

    <c:if test="${home.user.role.id == 2}">
        <%-- Employee --%>
        <div class="row">
            <c:if test="${home.unacceptedRequests.size() > 0}">
                <div class="col-md-6">
                        <%-- Display unaccepted requests --%>
                    <h3>Uaccepterede Forespørgsel</h3>
                    <a href="requests/unaccepted"
                       class="btn btn-default">Vis Alle</a>
                    <div class="table-responsive">
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th>Service Type</th>
                                <th>Beskrivelse</th>
                                <th></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${home.unacceptedRequests}"
                                       var="r">
                                <tr>
                                    <td>${r.serviceType.name}</td>
                                    <td>${r.description}</td>
                                    <td>
                                        <a href="request?id=${r.id}">Mere</a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </c:if>
            <c:if test="${home.acceptedRequests.size() > 0}">
                <div class="col-md-6">
                        <%-- Display accepted requests --%>
                    <h3>Accepterede Forespørgsel</h3>
                    <a href="requests/accepted?uid=${home.user.id}"
                       class="btn btn-default">Vis Alle</a>
                    <div class="table-responsive">
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th>Service Type</th>
                                <th>Beskrivelse</th>
                                <th></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${home.acceptedRequests}" var="r">
                                <tr>
                                    <td>${r.serviceType.name}</td>
                                    <td>${r.description}</td>
                                    <td>
                                        <a href="request?id=${r.id}">Mere</a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </c:if>
            <c:if test="${home.reports.size() > 0}">
                <div class="col-md-6">
                        <%-- Display reports --%>
                    <h3>Reporter</h3>
                    <a href="reports?uid=${home.user.id}"
                       class="btn btn-default">Vis Alle</a>
                    <div class="table-responsive">
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th>Bygnings navn</th>
                                <th>Tilføjelse</th>
                                <th></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${home.reports}" var="r">
                                <tr>
                                    <td>
                                        <a href="report?id=${r.id}">
                                                ${r.building.name}</a>
                                    </td>
                                    <td>
                                        <a href="report?id=${r.id}">
                                                ${r.submission.toGMTString()}</a>
                                    </td>
                                    <td>
                                        <a href="report?id=${r.id}">Mere</a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </c:if>
        </div>
    </c:if>

    <c:if test="${home.user.role.id == 3}">
        <%-- Admin --%>
        <div class="row">
            <c:if test="${home.orgs.size() > 0}">
                <div class="col-md-6">
                        <%-- Display organizations --%>
                    <h3>Organisationer</h3>
                    <a href="<c:url value="/orgs"/>" class="btn btn-default">Vis
                        Alle</a>
                    <div class="table-responsive">
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th>Navn</th>
                                <th>Tlf</th>
                                    <%--<th></th>--%>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${home.orgs}" var="o">
                                <tr>
                                    <td>
                                        <a href="org?id=${o.id}">${o.name}</a>
                                    </td>
                                    <td>
                                        <a href="org?id=${o.id}">${o.phone}</a>
                                    </td>
                                        <%--<td>
                                            <a href="org?id=${o.id}"
                                               class="btn btn-default">More</a>
                                        </td>--%>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </c:if>
            <c:if test="${home.users.size() > 0}">
                <div class="col-md-6">
                        <%-- Display users --%>
                    <h3>Kunder</h3>
                    <a href="customers" class="btn btn-default">Vis Alle</a>
                    <div class="table-responsive">
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th>Navn</th>
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
                                        <a href="customer?id=${u.id}">Mere</a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </c:if>
            <c:if test="${home.buildings.size() > 0}">
                <div class="col-md-6">
                        <%-- Display buildings --%>
                    <h3>Bygninger</h3>
                    <a href="buildings" class="btn btn-default">Vis Alle</a>
                    <div class="table-responsive">
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th>Navn</th>
                                <th>Adresse</th>
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
                                        <a href="building?id=${b.id}">Mere</a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </c:if>
            <c:if test="${home.requests.size() > 0}">
                <div class="col-md-6">
                        <%-- Display requests --%>
                    <h3>Forespørgsler</h3>
                    <a href="requests" class="btn btn-default">Vis Alle</a>
                    <div class="table-responsive">
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th>Service Type</th>
                                <th>Beskrivelse</th>
                                <th></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${home.requests}" var="r">
                                <tr>
                                    <td>${r.serviceType.name}</td>
                                    <td>${r.description}</td>
                                    <td>
                                        <a href="request?id=${r.id}">Mere</a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </c:if>
            <c:if test="${home.reports.size() > 0}">
                <div class="col-md-6">
                        <%-- Display reports --%>
                    <h3>Reporter</h3>
                    <a href="reports"
                       class="btn btn-default">Vis Alle</a>
                    <div class="table-responsive">
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th>Bygnings navn</th>
                                <th>Tilføjelse</th>
                                <th></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${home.reports}" var="r">
                                <tr>
                                    <td>
                                        <a href="report?id=${r.id}">
                                                ${r.building.name}</a>
                                    </td>
                                    <td>
                                        <a href="report?id=${r.id}">
                                                ${r.submission.toGMTString()}</a>
                                    </td>
                                    <td>
                                        <a href="report?id=${r.id}">Mere</a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </c:if>
        </div>
    </c:if>

</div>

<script src="<c:url value="/js/bootstrap.js"/>" type="text/javascript"></script>
<script src="<c:url value="/js/jquery-2.2.4.js"/>"
        type="text/javascript"></script>
</body>
</html>