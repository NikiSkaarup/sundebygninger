<%--
  Created by IntelliJ IDEA.
  User: Niki
  Date: 2016-11-29
  Time: 11:08 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="d" scope="request" class="model.File"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>File - Sundebygninger</title>
    <link href="<c:url value="/css/bootstrap.css"/>"
          rel="stylesheet" type="text/css"/>
</head>
<body>
<%@ include file="navigation.jsp" %>

<div class="container-fluid">
    <h1>${requestScope.action} File</h1>
    <div class="row">
        <c:if test="${requestScope.b > 0}">
            <div class="col-md-6">
                <form action="${requestScope.url}" method="post"
                      class="form-horizontal"
                      enctype="multipart/form-data">
                    <input type="hidden" value="${requestScope.b}" name="b"/>
                    <input type="hidden" value="${d.id}" name="id"/>

                    <div class="form-group">
                        <label class="col-md-3 control-label">Upload
                            Document</label>
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
        <c:if test="${d.name != null}">
            <div class="col-md-6">
                <a href="/documents/${d.path}" download="${d.name}"
                   class="btn btn-default">Download ${d.name}</a>
            </div>
        </c:if>
    </div>
</div>

<script src="<c:url value="/js/bootstrap.js"/>" type="text/javascript"></script>
<script src="<c:url value="/js/jquery-2.2.4.js"/>"
        type="text/javascript"></script>

</body>
</html>