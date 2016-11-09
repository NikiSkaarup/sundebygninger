<%--
  Created by IntelliJ IDEA.
  User: Niki
  Date: 2016-11-04
  Time: 2:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="r" scope="request" class="model.Request"/>
<jsp:useBean id="b" scope="request" class="model.Building"/>
<jsp:useBean id="sts" scope="request" type="java.util.ArrayList<model.ServiceType>"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Request - Sundebygninger</title>
    <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<%@ include file="navigation.jsp" %>

<div class="container">

    <form action="request" method="post">
        <input type="hidden" name="rId" value="${r.id}"/>
        <input type="hidden" name="bId" value="${b.id}"/>

        <div class="form-group">
            <label for="description">Beskrivelse:</label>
            <textarea class="form-control" id="description" name="description"
                      rows="5" placeholder="kort beskrivelse"
                      style="resize: vertical">${r.description}</textarea>
        </div>
        <div class="form-group">
            <label for="serviceType">Service type:</label>
            <select class="form-control" name="serviceType" id="serviceType">
                <c:forEach items="${sts}" var="st">
                    <option value="${st.id}" <c:if test="${r.serviceType.id == st.id}"> selected</c:if>>${st.name}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <button class="btn btn-primary" type="submit">Submit</button>
        </div>
    </form>

</div>

<script src="js/jquery-2.2.4.js" type="text/javascript"></script>
<script src="js/bootstrap.js" type="text/javascript"></script>
</body>
</html>
