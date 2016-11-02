<%-- 
    Document   : Allbuildings
    Created on : 27-10-2016, 22:41:55
    Author     : Menja
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <%@include file="navigation.jsp"  %>
        <div class="container-fluid">
            <h1>All Buildings</h1>
            <div class="table-responsive">
                <table class="table table-bordered table-hover table-striped">
                    <thead>
                        <tr class="active">
                            <th>Name</th>
                            <th>Address</th>
                            <th>Link</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>Lyngby</td>
                            <td>Kanalvej 4, 2800 Lyngby</td>
                            <td><a href="viewBuilding.jsp">Mere</a></td>
                        </tr>
                        <tr>
                            <td>Lyngby</td>
                            <td>Kanalvej 4, 2800 Lyngby</td>
                            <td><a href="viewBuilding.jsp">Mere</a></td>
                        </tr>
                        <tr>
                            <td>Lyngby</td>
                            <td>Kanalvej 4, 2800 Lyngby</td>
                            <td><a href="viewBuilding.jsp">Mere</a></td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <script src="Js/jquery-2.2.4.js" type="text/javascript"></script>
        <script src="Js/bootstrap.js" type="text/javascript"></script>
    </body>
</html>
