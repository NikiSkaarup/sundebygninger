<%--
  Created by IntelliJ IDEA.
  User: Niki
  Date: 2016-11-04
  Time: 11:27 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="requests" scope="request"
             type="java.util.List<model.Request>"/>
<jsp:useBean id="r" scope="request" class="model.Request"/>
<jsp:useBean id="sts" scope="request"
             type="java.util.ArrayList<model.ServiceType>"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Request - Sundebygninger</title>
    <link href="<c:url value="/css/bootstrap.css"/>"
          rel="stylesheet" type="text/css"/>
</head>
<body>
<%@ include file="navigation.jsp" %>

<div class="container">

    <div class="row">
        <c:if test="${requests.size() == 0 && requestScope.b > 0}">
            <div class="col-md-6">

                <h1>${requestScope.action} Forespørgsel</h1>
                    <%-- INSERT UPDATE --%>
                <form action="${requestScope.url}" method="post"
                      class="form-horizontal">
                    <input type="hidden" value="${requestScope.b}" name="b"/>
                    <input type="hidden" value="${r.id}" name="id"/>

                    <div class="form-group">
                        <label for="description">Beskrivelse:</label>
                        <textarea class="form-control" id="description"
                                  name="description"
                                  rows="5" placeholder="kort beskrivelse"
                                  style="resize: vertical" required>${r.description}</textarea>
                    </div>
                    <div class="form-group">
                        <label for="serviceType">Service type:</label>
                        <select class="form-control" name="serviceType"
                                id="serviceType">
                            <c:forEach items="${sts}" var="st">
                                <option value="${st.id}" <c:if
                                        test="${r.serviceType.id == st.id}"> selected</c:if>>
                                        ${st.name}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <button class="btn btn-primary" type="submit">
                                ${requestScope.action}
                        </button>
                    </div>
                </form>
            </div>
        </c:if>

        <c:if test="${r.description != null}">
            <div class="col-md-6">
                    <%-- VIEW --%>
                <a href="<c:url value="/requests?b=${r.building.id}"/>"
                   class="btn btn-default">Vis alle</a>
                <div class="panel panel-default">
                    <div class="panel-heading">Forespørgsel</div>
                    <div class="panel-body">
                        <p>${r.description}</p>
                    </div>
                    <div class="table-responsive">
                        <table class="table">
                            <tbody>
                            <tr>
                                <th>Service</th>
                                <td colspan="2">${r.serviceType.name}</td>
                            </tr>
                            <tr>
                                <th>Medarbejder tilknyttet</th>
                                <c:if test="${r.user != null}">
                                    <td><a href="mailto:${r.user.email}">
                                            ${r.user.name}</a></td>
                                    <td>${r.user.phone}</td>
                                </c:if>
                                <c:if test="${r.user == null}">
                                    <td colspan="2">Ingen</td>
                                </c:if>
                            </tr>
                            <tr>
                                <th>Rapport</th>
                                <td colspan="2">
                                    <c:if test="${r.report.id > 0}">
                                        <a href="<c:url value="/report?id=${r.id}"/>">Link</a>
                                    </c:if>
                                    <c:if test="${r.report.id < 1}">
                                        Ingen
                                    </c:if>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                        <div class="panel-footer">
                            <a href="<c:url value="/request/update?id=${r.id}"/>"
                               class="btn btn-default">Opdater</a>
                        </div>
                    </div>
                </div>
            </div>
        </c:if>

        <c:if test="${requests.size() > 0}">
            <div class="col-md-6">
                <div class="btn-group">
                    <a href="<c:url value="/request/insert?b=${requestScope.b}"/>"
                       class="btn btn-primary">Tilføj forespørgsel</a>
                </div>
                    <%-- VIEW ALL --%>
                <div class="table-responsive">
                    <table class="table table-bordered">
                        <tr>
                            <th>#</th>
                            <th>Beskrivelse</th>
                            <th>Service Type</th>
                            <th></th>
                        </tr>
                        <c:forEach items="${requests}" var="r">
                            <tr>
                                <td>${r.id}</td>
                                <td>${r.description}</td>
                                <td>${r.serviceType.name}</td>
                                <td>
                                    <a href="<c:url value="/request?id=${r.id}"/>">
                                        Mere</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
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
