<%-- 
    Document   : addBuildings
    Created on : 25-10-2016, 09:31:03
    Author     : Jamie
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="b" scope="request" class="model.Building"/>
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
        <%@ include file="navigation.jsp" %>
        <div class="container-fluid">
            
            <h1><c:out value="${requestScope.action}"/> bygning</h1>
            <div class="row">
                <div class="col-md-6"> 
                                
                    <form action="<c:out value="${requestScope.url}"/>" method="POST" class="form-horizontal">
                        <%--Id sendt from organisation--%>
                        <input type="hidden" value="<c:out value="${b.id}"/>" name="bId"/>
                        <input type="hidden" value="<c:out value="${org.id}"/>" name="orgId"/>

                        <div class="form-group">
                            <label class="col-md-3 control-label">Navn på bygning</label>
                            <div class="col-md-9">
                                <input class="form-control" type="text" value="<c:out value="${b.name}"/>" placeholder="Navn på bygning" name="Name" required/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-3 control-label">Adresse</label>
                            <div class="col-md-9">
                                <input class="form-control" type="text" value="<c:out value="${b.address}"/>" placeholder="Adresse" name="Address" required/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-3 control-label">Byggeår</label>
                            <div class="col-md-9">
                                <input class="form-control" type="text" value="<c:out value="${b.constructionYear}"/>" placeholder="yyyy-mm-dd" name="ConstructionYear" required/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-3 control-label">Bygningsareal i m2</label>
                            <div class="col-md-9">
                                <input class="form-control" type="text" value="<c:out value="${b.area}"/>" placeholder="Bygningsareal i m2" name="Area" required/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-3 control-label">Nuværende benyttelse</label>
                            <div class="col-md-9">
                                <input class="form-control" type="text" value="<c:out value="${b.currentUse}"/>" placeholder="Nuværende benyttelse" name="CurrentUse" required/>
                                <label>(Hvad bruges bygningen til?)</label>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-3 control-label">Tidligere benyttelse</label>
                            <div class="col-md-9">
                                <input class="form-control" type="text" value="<c:out value="${b.previousUse}"/>" placeholder="tidligere benyttelse" name="PreviousUse" required/>
                                <label>(Hvad har bygningen været brugt til?)</label>
                            </div>
                        </div>

                        <div class="col-md-offset-3">
                            <button class="btn btn-primary" type="submit"><c:out value="${requestScope.action}"/></button>
                        </div>

                    </form>
                </div>
            </div>
        </div>
        <script src="<c:url value="/js/bootstrap.js"/>" type="text/javascript"></script>
        <script src="<c:url value="/js/jquery-2.2.4.js"/>"
        type="text/javascript"></script>
    </body>
</html>
