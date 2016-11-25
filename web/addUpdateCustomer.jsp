<%-- 
    Document   : addUpdateCustomer
    Created on : 03-11-2016, 11:23:02
    Author     : Tanja
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="u" scope="request" class="model.User"/>
<jsp:useBean id="org" scope="request" class="model.Org"/>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="<c:url value="/css/bootstrap.css"/>"
              rel="stylesheet" type="text/css"/>
    </head>

    <body>
        <%@include file="navigation.jsp" %>
        <div class="container-fluid">
            <form action="<c:out value="${requestScope.url}"/>" method="POST" class="form-horizontal">
                <input type="hidden" value="${u.id}" name="uid"/>
                <input type="hidden" value="${org.id}" name="oid"/>

                <h4>${requestScope.action}Redigere kunde</h4>
                <table>
                    <div class="form-group">
                        <label class="col-md-3 control-label">ID:</label>
                        <div class="col-md-9">
                            <input class="form-control" type="text" value="${u.id}" placeholder="Kunde id" name="uid"/> 
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-3 control-label">Navn:</label>
                        <div class="col-md-9">
                            <input class="form-control" type="text" value="${u.name}" placeholder="Fulde navn" name="name"/> 
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-3 control-label">Email:</label>
                        <div class="col-md-9">
                            <input class="form-control" type="text" value="${u.email}" placeholder="Email" name="email"/> 
                        </div>
                    </div>

                    <div>
                        <label class="col-md-3 control-label">Tlf. nummer:</label>
                        <div>
                            <input class="form-control" type="text" value="${u.phone}" placeholder="Nummer" name="phone"/> 
                        </div>
                    </div>
                    <div>
                        <a href="/customer/update?id=${u.id}">Redigere kunde</a>
                    </div>
                </table>
        </div>
        <script src="js/jquery-2.2.4.js" type="text/javascript"></script>
        <script src="js/bootstrap.js" type="text/javascript"></script>

    </body>
</html>

