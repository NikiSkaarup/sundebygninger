<%-- 
    Document   : addUpdateCustomer
    Created on : 03-11-2016, 11:23:02
    Author     : Tanja
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>

        <title>Add/update a customer</title>
    </head>
    <body>
        <%@include file="navigation.jsp"  %>
        <div class="container-fluid">
            <h4>Tilføj/redigere kunde</h4>
            <div class="row" class="col-md-6"></div>
                <form method="POST" action="###" class="form-horizontal">
                    <div class="form-group"> 
                        <label class="col-md-3" for="Kunde id">Kunde id:</label>
                        <div class="col-md-9">   
                            <input type="ID" class="form-control" id="Kunde id" 
                                   placeholder="Enter id" name="id">
                        </div>


                        <div class="col-md-offset-3">
                            
                            <button class="btn btn-primary">OK</button>
                        </div>
                </form>
            </div>
            <script src="Js/jquery-2.2.4.js" type="text/javascript"></script>
            <script src="Js/bootstrap.js" type="text/javascript"></script>
    </body>
</html>
