<%-- 
    Document   : addBuildings
    Created on : 25-10-2016, 09:31:03
    Author     : Jamie
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

            <h1>Add building</h1>
            <div class="row">
                <div class="col-md-6"> 
                    <form action="buildingController.jsp" method="POST" class="form-horizontal">
                        <div class="form-group">
                            <label class="col-md-3 control-label">Navn på bygning</label>
                            <div class="col-md-9">
                                <input class="form-control" type="text"  placeholder="Navn på bygning" name="Name"/> 
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-3 control-label">Adresse</label>
                            <div class="col-md-9">
                                <input class="form-control" type="text" placeholder="Adresse" name="Address"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-3 control-label">Billeder af hus eller bygning</label>
                            <div class="col-md-9">
                                <input type="file" name="image"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-3 control-label">Byggeår</label>
                            <div class="col-md-9">
                                <input class="form-control" type="text" placeholder="Byggeår" name="ConstructionYear"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-3 control-label">Bygningsareal i m2</label>
                            <div class="col-md-9">
                                <input class="form-control" type="text" placeholder="Bygningsareal i m2" name="Area"/>
                                <label>(Hver etage tælles seperat)</label>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-3 control-label">Nuværende benyttelse</label>
                            <div class="col-md-9">
                                <input class="form-control" type="text" placeholder="Nuværende enyttelse" name="CurrentUse"/>
                                <label>(Hvad bruges bygningen til?)</label>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-3 control-label">Tidligere benyttelse</label>
                            <div class="col-md-9">
                                <input class="form-control" type="text" placeholder="tidligere benyttelse" name="PreviousUse"/>
                                <label>(Hvad har bygningen været brugt til?)</label>
                            </div>
                        </div>
                        
                        <div class="col-md-offset-3">
                            <button class="btn btn-primary" action="buildingController.java">Send</button>
                        </div>

                    </form>
                </div>
            </div>
        </div>
        <script src="Js/jquery-2.2.4.js" type="text/javascript"></script>
        <script src="Js/bootstrap.js" type="text/javascript"></script>
    </body>
</html>
