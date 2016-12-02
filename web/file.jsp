<%--
  Created by IntelliJ IDEA.
  User: Niki
  Date: 2016-11-29
  Time: 11:08 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="f" scope="request" class="model.File"/>
<jsp:useBean id="fts" scope="request"
             type="java.util.ArrayList<model.FileType>"/>
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
                    <input type="hidden" value="${f.id}" name="id"/>

                    <div class="form-group">
                        <label class="col-md-3 control-label"
                               for="file">Upload File</label>
                        <div class="col-md-9">
                            <input id="file" type="file" name="file" required/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-3 control-label"
                               for="fileType">File type:</label>
                        <div class="col-md-9">
                            <select class="form-control" name="fileType"
                                    id="fileType">
                                <c:forEach items="${fts}" var="ft">
                                    <option value="${ft.id}" <c:if
                                            test="${f.type.id == ft.id}"> selected</c:if>>
                                            ${ft.name}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <div class="col-md-offset-3">
                        <button class="btn btn-primary" type="submit">Send
                        </button>
                    </div>
                </form>
            </div>
        </c:if>
        <c:if test="${f.id > 0}">
            <div class="col-md-6">
                <a href="/<c:if
                test="${f.type.id == 1}">document</c:if><c:if
                test="${f.type.id == 2}">image</c:if>/${f.id}"
                   download="${f.name}"
                   class="btn btn-default">Download ${f.name}</a>
            </div>
        </c:if>
        <c:if test="${f.type.id == 2}">
            <div class="col-md-6">
                <img src="/image/${f.id}" alt="${f.name}"
                     class="img-responsive"/>
            </div>
        </c:if>
    </div>
</div>

<script src="<c:url value="/js/bootstrap.js"/>" type="text/javascript"></script>
<script src="<c:url value="/js/jquery-2.2.4.js"/>"
        type="text/javascript"></script>

</body>
</html>