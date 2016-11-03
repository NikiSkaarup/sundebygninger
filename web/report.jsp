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
            <hr>
            <div class="row">
                <div class="col-md-8">
                    <form method="POST" action="ReportController" class="form-horizontal">
                        <div class="form-group">
                            <label class="col-md-3 control-label">Bygning</label>
                            <div class="col-md-9">
                                <label for="building">Bygning</label>
                                //TODO: fix somthing with buildings
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-3 control-label">Tag</label>
                            <div class="col-md-9">
                                <textarea class="form-control" rows="2" placeholder="Bemærkninger:" name="roof"></textarea>
                                <span>Billede: <input type="file"/></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">Ydervægge</label>
                            <div class="col-md-9">
                                <textarea class="form-control" rows="2" placeholder="Bemærkninger:" name="outerWalls"></textarea>
                                <span>Billede: <input type="file"/></span>
                            </div>
                        </div>

                        <div id="roomContainer"></div>
                        <hr>
                        <a id="add_room" class="btn btn-default col-md-offset-3">Tilføj lokale</a>
                        <hr>
                        <div class="col-md-offset-3">
                            <button class="btn btn-primary">Send</button>
                        </div>

                    </form>
                </div>
            </div>
        </div>
        <script src="js/jquery-2.2.4.js" type="text/javascript"></script>
        <script src="js/bootstrap.js" type="text/javascript"></script>

        <script src="js/report.js" type="text/javascript"></script>
    </body>
</html>
