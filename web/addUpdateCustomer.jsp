<%-- 
    Document   : addUpdateCustomer
    Created on : 03-11-2016, 11:23:02
    Author     : Tanja
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="u" scope="request" class="model.User"/>
<jsp:useBean id="o" scope="request" class="model.Org"/>
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
            <h4>Redigere/Opret kunde</h4>
            <div class="row">
                <div class="col-md-6">
                    <form action="<c:out value="${requestScope.url}"/>" method="POST" class="form-horizontal">
                        <input type="hidden" value="<c:out value="${u.id}"/>" name="uid"/>
                        <input type="hidden" value="<c:out value="${o.id}"/>" name="oid"/>

                        <div class="form-group">
                            <label class="col-md-3 control-label">Navn:</label>
                            <div class="col-md-9">
                                <input class="form-control" type="text" value="<c:out value="${u.name}"/>" placeholder="Fulde navn" name="name" required/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">Email:</label>
                            <div class="col-md-9">
                                <input class="form-control" type="email" value="<c:out value="${u.email}"/>" placeholder="Email" name="email" required/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">Tlf. nummer:</label>
                            <div class="col-md-9">
                                <input class="form-control" type="text" value="<c:out value="${u.phone}"/>" placeholder="Nummer" name="phone" required/>
                            </div>
                        </div>
                        <c:if test="${u.id < 1}">
                        <div class="form-group">
                            <label class="col-md-3 control-label">Kodeord</label>
                            <div class="col-md-9">
                                <input class="form-control" type="password"
                                       placeholder="Kodeord" name="password" required/>
                            </div>
                        </div>
                        </c:if>
                        <div>
                            <button class="btn btn-primary" type="submit">GEM</button>
                        </div>
                    </form>

                </div>

            </div>
        </div>


        <script src="js/jquery-2.2.4.js" type="text/javascript"></script>
        <script src="js/bootstrap.js" type="text/javascript"></script>

    </body>
</html>

