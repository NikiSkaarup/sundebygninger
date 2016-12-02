<%--
  Created by IntelliJ IDEA.
  User: Niki
  Date: 2016-10-26
  Time: 11:38 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed"
                    data-toggle="collapse" data-target="#navbar-collapse"
                    aria-expanded="false">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="<c:url value="/home"/>"><img
                    src="<c:url value="/billeder/SundeBygningerLogo.png"/>"
                    style="height:43px; width:108px"/></a>
        </div>

        <div class="collapse navbar-collapse" id="navbar-collapse">
            <ul class="nav navbar-nav">
                <c:if test="${requestScope.links != null}">
                    <c:forEach items="${requestScope.links}" var="link">
                        <li>
                            <a href="<c:url value="${link.path}"/>">${link.name}</a>
                        </li>
                    </c:forEach>
                </c:if>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="<c:url value="/logout"/>">log ud</a></li>
            </ul>
        </div>
    </div>
</nav>
