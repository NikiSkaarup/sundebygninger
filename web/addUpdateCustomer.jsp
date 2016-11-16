<%-- 
    Document   : addUpdateCustomer
    Created on : 03-11-2016, 11:23:02
    Author     : Tanja
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="u" scope="request" class="model.User"/>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>

        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="navigation.jsp"  %>
        <div class="container-fluid">

            <h4>${requestScope.action}Redigere kunde</h4>
            <div class="row">
                <div class="col-md-6"> 
                    <form action="CustomerController" method="POST" class="form-horizontal">
                        <%--UserId sendt from organisation--%>
                        <input type="hidden" value="${u.id}" name="uId"/>
                        <input type="hidden" value="${o.Id}" name="oId"/>

                        <div class="form-group">
                            <label class="col-md-3 control-label">ID:</label>
                            <div class="col-md-9">
                                <input class="form-control" type="text" value="${u.id}" placeholder="Kunde id" name="id"/> 
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-3 control-label">Navn:</label>
                            <div class="col-md-9">
                                <input class="form-control" type="text" value="${u.name}" placeholder="Fulde navn" name="Name"/> 
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-3 control-label">Email:</label>
                            <div class="col-md-9">
                                <input class="form-control" type="text" value="${u.email}" placeholder="Email" name="Email"/> 
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-3 control-label">Tlf. nummer:</label>
                            <div class="col-md-9">
                                <input class="form-control" type="text" value="${u.phone}" placeholder="Nummer" name="Phone"/> 
                            </div>
                        </div>


                        <button type="submit" class="btn btn-primary">${requestScope.action} Opdatere</button>
                        <button type="submit" class="btn btn-primary">${requestScope.action} Tilføj ny kunde</button>
                    </form>
                </div>
            </div>
        </div> 

        <script src="js/jquery-2.2.4.js" type="text/javascript"></script>
        <script src="js/bootstrap.js" type="text/javascript"></script>
    </body>
</html>
