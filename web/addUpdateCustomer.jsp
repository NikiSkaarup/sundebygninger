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

            <h4>Redigere kunde</h4>
            <div class="row">
                <div class="col-md-6"> 
                    <form action="ViewCustomer" method="POST" class="form-horizontal" enctype="multipart/form-data">
                        <input type="hidden" value="???" name="????"/>

                        <div class="form-group">
                            <label class=col-md-3 control-label>ID:</label>

                            <input type="id" class="form-control" placeholder="ID" id="kunde-id">           
                        </div>

                        <div class="form-group">
                            <label class=col-md-3 control-label>Fulde navn:</label>
                            <input type="navn" class="form-control" placeholder="Fulde navn" id="navn">
                        </div>

                        <div class="form-group">
                            <label class=col-md-3 control-label>Email:</label>
                            <input type="mail" class="form-control" placeholder="Email" id="email">
                        </div>

                        <div class="form-group">
                            <label class=col-md-3 control-label>Tlf. nummer:</label>
                            <input type="tlf." class="form-control" placeholder="Tlf. nummer" id="tlf. nummer">
                        </div>

                        <button type="submit" class="btn btn-primary">OK</button>
                    </form>
                </div>
            </div>
        </div> 
    </form

    <script src="js/jquery-2.2.4.js" type="text/javascript"></script>
    <script src="js/bootstrap.js" type="text/javascript"></script>
</body>
</html>
