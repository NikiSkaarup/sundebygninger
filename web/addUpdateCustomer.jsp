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
            <div class="row">
                <div class="col-md-6"> 
                    <form action="Customer" method="POST" class="form-horizontal" enctype="multipart/form-data">
                        <input type="hidden" value="???" name="????"/>

                        <div class="form-group">
                            <label class=col-md-3 control-label>Kunde-id:</label>

                            <input type="id" class="form-control" id="kunde-id">           
                        </div>

                        <div class="form-group">
                            <label class=col-md-3 control-label>Navn:</label>
                            <input type="navn" class="form-control" id="navn">
                        </div>

                        <div class="form-group">
                            <label class=col-md-3 control-label>Email:</label>
                            <input type="mail" class="form-control" id="email">
                        </div>

                        <div class="form-group">
                            <label class=col-md-3 control-label>Tlf:</label>
                            <input type="tlf." class="form-control" id="tlf. nummer">
                        </div>

                        <button type="submit" class="btn btn-primary">Tilføj</button>
                    </form>
                </div>
            </div>
        </div> 
    </form

    <script src="js/jquery-2.2.4.js" type="text/javascript"></script>
    <script src="js/bootstrap.js" type="text/javascript"></script>
</body>
</html>
