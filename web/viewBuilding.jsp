<%-- 
    Document   : viewBuilding
    Created on : 02-11-2016, 13:45:34
    Author     : Menja
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="b" scope="request" class="model.Building"/>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <%@include file="navigation.jsp" %>

        <div class="container-fluid">
            <div class="row">
                <div class="col-md-6">
                    <h1>Bygning</h1>

                    <p>Organisation: ${b.org.name}</p>
                    <p>Navn på bygning: ${b.name}</p>
                    <p>Adresse: ${b.address}</p>
                    <p>Byggeår: ${b.constructionYear}</p>
                    <p>Nuværende benyttelse: ${b.currentUse}</p>
                    <p>Areal: ${b.area}</p>
                    <p>Tidlige benyttelse: ${b.previousUse}</p>

                    <a href="/file/insert?b=${b.id}">Tilføj billede/dokument</a>
                    <a href="/request/insert?b=${b.id}">Tilføj forespørgsel</a>
                    <a href="/requests?b=${b.id}">Vis alle forespørgsler</a>
                    <a href="/building/update?id=${b.id}">Rediger bygning</a>

                    </div>
                <c:if test="${b.documents.size() > 0}">
                    <div class="col-md-6">
                        <div class="list-group">
                            <c:forEach items="${b.documents}" var="d">
                                <a class="list-group-item"
                                   href="${d.path}"
                                   download="${d.name}">download ${d.name}</a>
                            </c:forEach>
                        </div>
                    </div>
                </c:if>
                <c:if test="${b.images.size() > 0}">
                    <div class="col-md-12">
                        <div class="row">
                            <c:forEach items="${b.images}" var="i">
                                <div class="col-md-3">
                                    <a class="thumbnail"
                                       href="${i.path}"
                                       download="${i.name}">
                                        <img src="${i.path}"
                                             alt="${i.name}">
                                    </a>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </c:if>
            </div>
        </div>
        <script src="js/jquery-2.2.4.js" type="text/javascript"></script>
        <script src="js/bootstrap.js" type="text/javascript"></script>
    </body>
</html>
