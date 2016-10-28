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
                <div class="col-md-6">
                    <form method="POST" action="ReportController" class="form-horizontal">
                        <div class="form-group">
                            <label class="col-md-3 control-label">Bygning</label>
                            <div class="col-md-9">
                                <label for="building">Vælg bygning</label>
                                <select class="form-control" id="building">
                                    <option>Bygning 1</option>
                                    <option>Bygning 2</option>
                                    <option>Bygning 3</option>
                                    <option>Bygning 4</option>
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-3 control-label">Tag</label>
                            <div class="col-md-9">
                                <label for="roof">Bemærkninger:</label>
                                <textarea class="form-control" rows="2" id="roof"></textarea>
                                <span>Billede: <input type="file"/></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">Ydervægge</label>
                            <div class="col-md-9">
                                <label for="outerWalls">Bemærkninger:</label>
                                <textarea class="form-control" rows="2" id="outerWalls"></textarea>
                                <span>Billede: <input type="file"/></span>
                            </div>
                        </div>



                        <div class="form-group">
                            <hr>
                            <label class="col-md-3 control-label">Lokale</label>
                            <div class="col-md-9">
                                <input class="form-control" type="text" placeholder="Lokale ID" name="room"/>

                                <div>
                                    Har der været skade i lokalet? 
                                    <label><input type="radio" name="optradio">Ja</label>
                                    <label><input type="radio" name="optradio">Nej</label>
                                </div>


                                <div class="col-md-6">
                                    Hvornår?   
                                    <input type="date" class="form-control" name="date">
                                </div>
                                <div class="col-md-6">
                                    Hvor?   
                                    <input type="text" class="form-control" name="location">                               
                                </div>

                                <div class="col-md-6">
                                    Hvad er der sket?   
                                    <input type="text" class="form-control" name="incident">
                                </div>
                                <div class="col-md-6">
                                    Hvad er repareret?   
                                    <input type="text" class="form-control" name="repair">                             
                                </div>



                                <div>
                                    Skadetype? 
                                    <label><input type="checkbox" name="optradio">Fugt</label>
                                    <label><input type="checkbox" name="optradio">Råd og svamp</label>
                                    <label><input type="checkbox" name="optradio">Skimmel</label>
                                    <label><input type="checkbox" name="optradio">Brand</label>
                                    <label><input type="checkbox" name="optradio">Andet</label>
                                </div>


                                <div>
                                    Bemærkninger:
                                    <textarea class="form-control" rows="2" name="roomComment"></textarea>
                                    <span>Billede: <input type="file"/></span>
                                </div>
                                <hr>
                                <a id="add_room" class="btn btn-default pull-left">Tilføj lokale</a>
                            </div>
                        </div>
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
