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
        <div class="container-fluid">

            <h1>Add building</h1>
            <div class="row">
                <div class="col-md-6"> 
                    <form action="action" class="form-horizontal">
                        <div class="form-group">
                            <label class="col-md-3 control-label">Navn på bygning</label>
                            <div class="col-md-9">
                                <input class="form-control" type="text"  value="Navn på bygning"/> 
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-3 control-label">Adresse</label>
                            <div class="col-md-9">
                                <input class="form-control" type="text" value="Adresse"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-3 control-label">Postnummer</label>
                            <div class="col-md-9">
                                <input class="form-control" type="text" value="Postnummer"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-3 control-label">By</label>
                            <div class="col-md-9">
                                <input class="form-control" type="text" value="By"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-3 control-label">Billeder af hus eller bygning</label>
                            <div class="col-md-9">
                                <input type="file"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-3 control-label">Byggeår</label>
                            <div class="col-md-9">
                                <input class="form-control" type="text" value="Byggeår"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-3 control-label">Bygningsareal i m2</label>
                            <div class="col-md-9">
                                <input class="form-control" type="text" value="Bygningsareal i m2"/>
                                <label>(Hver etage tælles seperat)</label>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-3 control-label">Benyttelse</label>
                            <div class="col-md-9">
                                <input class="form-control" type="text" value="Benyttelse"/>
                                <label>(Hvad bruges bygningen til? / Hvad har bygningen været brugt til?)</label>
                            </div>
                        </div>

                        <div class="col-md-offset-3">
                            <button class="btn btn-primary">Send</button>
                        </div>

                    </form>
                </div>
            </div>
        </div>
        <script src="Js/bootstrap.js" type="text/javascript"></script>
        <script src="Js/jquery-2.2.4.js" type="text/javascript"></script>
    </body>
</html>
