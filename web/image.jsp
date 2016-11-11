<%--
  Created by IntelliJ IDEA.
  User: Niki
  Date: 2016-10-27
  Time: 9:52 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="i" scope="request" class="model.Image"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Image - Sundebygninger</title>
    <link href="<c:url value="/css/bootstrap.css"/>"
          rel="stylesheet" type="text/css"/>
</head>
<body>
<%@ include file="navigation.jsp" %>

<div class="container-fluid">
    <h1>${requestScope.action} Image</h1>
    <div class="row">
        <c:if test="${requestScope.b > 0}">
            <div class="col-md-6">
                <form action="${requestScope.url}" method="post"
                      class="form-horizontal"
                      enctype="multipart/form-data">
                    <input type="hidden" value="${requestScope.b}" name="b"/>
                    <input type="hidden" value="${i.id}" name="id"/>

                    <div class="form-group">
                        <label class="col-md-3 control-label">Upload
                            Image</label>
                        <div class="col-md-9">
                            <input type="file" name="file"/>
                        </div>
                    </div>

                    <div class="col-md-offset-3">
                        <button class="btn btn-primary" type="submit">Send
                        </button>
                    </div>
                </form>
            </div>
        </c:if>
        <c:if test="${i.name != null}">
            <div class="col-md-6">
                <div class="row">
                    <div class="col-md-4">
                        <a href="/images/${i.path}" download="${i.name}"
                           class="btn btn-default">Download ${i.name}</a>
                    </div>
                    <div class="col-md-8">
                        <a href="/images/${i.path}" download="${i.name}">
                            <img src="/images/${i.path}" alt="${i.name}"
                                 class="img-responsive"/></a>
                    </div>
                </div>
            </div>
        </c:if>
    </div>
</div>

<script src="<c:url value="/js/bootstrap.js"/>" type="text/javascript"></script>
<script src="<c:url value="/js/jquery-2.2.4.js"/>"
        type="text/javascript"></script>

</body>
</html>
