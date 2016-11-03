<%-- 
    Document   : viewCustomer
    Created on : 03-11-2016, 09:19:11
    Author     : Tanja
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>

        <title>List for one customer</title>
    </head>
    <body>
        <%@include file="navigation.jsp"  %>
        <h4>Oplysninger om den pågældende kunde</h4>
        <table border="1" class="col-md-4">
            <body>
            <tr>
                <th>ID</th>
                <td>Kunde id</td>
            </tr>
            <tr>
                <th>Navn</th>
                <td>Kunde navn</td>
            </tr>
            <tr>
                <th>Email</th>
                <td>Kunde email</td>
            </tr>
            <tr>
                <th>Tlf.nummer</th>
                <td>Kunde tlf.nummer</td>
            </tr>
            <p>
                <a href="###">Redigere kunde</a>
            </p>
    </body>
</table>
<script src="js/jquery-2.2.4.js" type="text/javascript"></script>
<script src="js/bootstrap.js" type="text/javascript"></script>
</div>
</body>
</html>
