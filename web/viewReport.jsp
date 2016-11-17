<%-- 
    Document   : viewReport
    Created on : 16-11-2016, 14:45:42
    Author     : Jamie
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="r" scope="request" class="model.Report"/>
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
                <h1>Rapport #${r.id}</h1>

                <p>Rapport dato: ${r.submission}</p>
                <p>Navn på bygning: ${r.building.name}</p>

                <c:forEach items="${r.comments}" var="c">
                    <table class="table table-bordered">
                        <tr>
                            <td>Placering:</td>
                            <td>
                                ${c}
                            </td>
                        </tr><tr>
                            <td>Bemærkning:</td>
                            <td>

                                ${c}
                            </td>
                        </tr><tr>
                            <td>Evt billede(r):</td>
                            <td>

                            </td>
                        </tr>
                    </table>
                </c:forEach>

            </div>
        </div>
        <script src="js/jquery-2.2.4.js" type="text/javascript"></script>
        <script src="js/bootstrap.js" type="text/javascript"></script>
    </body>
</html>
