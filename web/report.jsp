<%-- 
    Document   : report
    Created on : 26-10-2016, 13:02:00
    Author     : Jamie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Rapport</title>
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <%@include file="navigation.jsp"  %>
        <div class="container-fluid">

            <h1>Sundhedstjek rapport</h1>
            <div class="row">
                <div class="col-md-6"> 
                    <form action="action" class="form-horizontal">
                        <div class="form-group">
                            <label class="col-md-3 control-label">Bygning</label>
                            <div class="col-md-9">
                                <div class="dropdown">
                                    <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Vælg bygning
                                        <span class="caret"></span></button>
                                    <ul class="dropdown-menu">
                                        <li><a href="#">Bygning 1</a></li>
                                        <li><a href="#">Bygning 2</a></li>
                                        <li><a href="#">Bygning 3</a></li>
                                    </ul>
                                </div> 
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-3 control-label">Adresse</label>
                            <div class="col-md-9">
                                <input class="form-control" type="text" value="Adresse"/>
                            </div>
                        </div>

                        

                        <div class="form-group">
                            <label class="col-md-3 control-label">Billeder af hus eller bygning</label>
                            <div class="col-md-9">
                                <input type="file"/>
                            </div>
                        </div>


                        
                        <div class="col-md-offset-3">
                            <button class="btn btn-primary">Send</button>
                        </div>

                    </form>
                </div>
            </div>
        </div>
        <script src="Js/jquery-2.2.4.js" type="text/javascript"></script>
        <script src="Js/bootstrap.js" type="text/javascript"></script>
    </body>
</html>
