<%-- 
    Document   : login
    Created on : 26-10-2016, 22:51:17
    Author     : Tanja
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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

        <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>

    </head>
    <body>
        <div class="container-fluid">
            <a href="building.jsp">Add/update building</a>
            <h1> Polygon A/S </h1>
            
            <h2>Sign in as customer/admin</h2>
             
            <div class="row">
                <div class="col-md-6">
                    <!--Add email-->
                    <form method="POST" action="LoginController" class="form-horizontal">
                        <div class="form-group"> 
                            <label class="col-md-3" for="email">Email:</label>
                            <div class="col-md-9">   
                                <input type="email" class="form-control" id="email" 
                                       placeholder="Enter email" name="email">
                            </div>
                        </div>
                        <div id="formholder">
                    <!--Add password-->
                        <div class="form-group">
                            <label class="col-md-3" for="pwd">Password:</label>
                            <div class="col-md-9">
                                <input type="password" class="form-control" 
                                       id="pwd" placeholder="Enter password" name="password">
                            </div>
                        </div>

                        <div class="checkbox col-md-offset-3">
                            <label><input type="checkbox"> Remember me</label>
                        </div>
                        <div class="col-md-offset-3">
                            <button type="submit" class="btn btn-default">Submit</button>
                        </div>
                    </form>
                </div>
            </div>
            <script src="js/jquery-2.2.4.js" type="text/javascript"></script>
            <script src="js/bootstrap.js" type="text/javascript"></script>
        </div>
    </body>
</html>
