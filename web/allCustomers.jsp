<%-- 
    Document   : ViewAllCustomers
    Created on : 02-11-2016, 12:13:31
    Author     : Tanja
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>

        <title>JSP list for all customers</title>
    </head>
    <body>
        <%@include file="navigation.jsp"  %>
        <h3>Liste over alle kunderne</h3>
        <table class="table table-hower">
        <div>
                <thead>
                    <tr>
                        <th>Nr.</th>
                        <th>Navn</th>
                        <th>Email</th>
                        <th>Link til info</th>
                        <th>Redigere</th>
                    </tr>  
                </thead>
        </div>
    <tr>
        <th scope="row">1</th>
        <td>?</td>
        <td>?</td>
        <td><a href="#">Link</a> </td>
        <td><a href="##">Redigere</a> </td>
    </tr>
    <tr>
        <th scope="row">2</th>
        <td>?</td>
        <td>?</td>
        <td><a href="#">Link</a> </td>
        <td><a href="##">Redigere</a> </td>
    </tr>
    <tr>
        <th scope="row">3</th>
        <td>?</td>
        <td>?</td>
        <td><a href="#">Link</a> </td>
        <td><a href="##">Redigere</a> </td>
    </tr>


    <form>


    </form>
</div>
</div>
<script src="js/jquery-2.2.4.js" type="text/javascript"></script>
<script src="js/bootstrap.js" type="text/javascript"></script>
</div>
</body>
</html>
