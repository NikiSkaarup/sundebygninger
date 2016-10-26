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
        <link href="CSS/bootstrap.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="container-fluid">
            <h1>Add building</h1>
            
            <form action="action">
                <div></div>
                <input type="text"  value="Navn på bygning"/> 
                
                <input type="text" value="Addresse"/>
                
                <input type="text" value="Postnummer"/>
                
                <input type="file" value="Billede af hus/bygning"/>
                
                <h4>Generel information</h4>
                <input type="text" value="Byggeår"/>
                <input type="text" value="Bygningsareal i m2"/>
                <label>(Hver etage tælles seperat)</label>
                <input type="text" value="Benyttelse"/>
                <label>(Hvad bruges bygningen til? / Hvad har bygningen været brugt til?)</label>
            </form>
            
        </div>
        <script src="Js/bootstrap.js" type="text/javascript"></script>
        <script src="Js/jquery-2.2.4.js" type="text/javascript"></script>
    </body>
</html>
