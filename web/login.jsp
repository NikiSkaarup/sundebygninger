<%-- 
    Document   : login
    Created on : 26-10-2016, 22:51:17
    Author     : Tanja
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
<head>
    <title>Sunde bygninger - Polygon a/s</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link href="<c:url value="/css/bootstrap.css"/>" rel="stylesheet"
          type="text/css"/>

</head>
<body>
<div class="container-fluid">
    <div style="text-align:left">
        <img src="billeder/PolygonLogo.png" style="height:44px; width:250px" > <br>
        <div style="text-align:center">
            
            <img src="billeder/SundeBygningerLogo.png" style="height:160px; width:400px" > 
            <br><br><br>
        </div>
    </div>


    <div class="row" style="align-items: center">
        <div class="col-md-3"></div>
        <div class="col-md-6">
            <!--Add email-->
            <form method="POST" action="LoginController"
                  class="form-horizontal">
                <div class="form-group">
                    <label class="col-md-2" for="email">Email:</label>
                    <div class="col-md-10">
                        <input type="email" class="form-control" id="email"
                               placeholder="Enter email" name="email" autofocus>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-2" for="pwd">Password:</label>
                    <div class="col-md-10">
                        <input type="password" class="form-control"
                               id="pwd" placeholder="Enter password"
                               name="password">
                    </div>
                </div>

                <div class="col-md-offset-2">
                    <button type="submit" class="btn btn-default">Sign in
                    </button>
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
