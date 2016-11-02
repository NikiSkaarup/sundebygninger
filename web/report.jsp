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



                        <div class="form-group" id="room-1">
                            <hr>
                            <label class="col-md-3 control-label">Lokale 1</label>
                            <div class="col-md-9">
                                <input class="form-control" type="text" placeholder="Lokale ID" name="room"/>

                                <div>
                                    Har der været skade i lokalet? 
                                    <label><input type="radio" name="damradio">Ja</label>
                                    <label><input type="radio" name="damradio">Nej</label>
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
                                    <label><input type="checkbox" value="mold" >Fugt</label>
                                    <label><input type="checkbox" value="" >Råd og svamp</label>
                                    <label><input type="checkbox" value="" >Skimmel</label>
                                    <label><input type="checkbox" value="" >Brand</label>
                                    <label><input type="checkbox" value="" >Andet</label>
                                </div>
                                <hr>

                                <select class="form-control" id="roomComentType-1">
                                    <option>Vægge</option>
                                    <option>Loft</option>
                                    <option>Gulv</option>
                                    <option>Vinduer/døre</option>                                    
                                    <option>Andet</option>
                                </select>
                                <div>
                                    Bemærkninger:
                                    <textarea class="form-control" rows="2" name="roomComment"></textarea>
                                    <span>Billede: <input type="file"/></span>
                                </div>
                                
                                <div>
                                    <a id="add_comment" class="btn btn-default">Tilføj bemærkninger</a>
                                </div>

                                <hr>
                                <div>
                                    Fugtighedsscanning? 
                                    <label><input type="radio" name="moistradio-1">Ja</label>
                                    <label><input type="radio" name="moistradio-1">Nej</label>
                                </div>      

                                <div class="col-md-6">
                                    Fugtscanning:  
                                    <input type="date" class="form-control" name="moistureScan-1">
                                </div>
                                <div class="col-md-6">
                                    Målepunkt   
                                    <input type="text" class="form-control" name="measureSpot-1">                               
                                </div>

                                <br>
                            </div>
                        </div>
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
