<%--
  Created by IntelliJ IDEA.
  User: Niki
  Date: 2016-10-26
  Time: 1:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<%@ include file="navigation.jsp" %>

<div class="container-fluid">

    <h1>${action} Document</h1>
    <div class="row">
            <div class="col-md-6">
            <form action="document" method="post" class="form-horizontal" enctype="multipart/form-data">
                <div class="form-group">
                    <label class="col-md-3 control-label">Upload Document</label>
                    <div class="col-md-9">
                        <input type="file" name="file"/>
                    </div>
                </div>

                <div class="col-md-offset-3">
                    <button class="btn btn-primary">Send</button>
                </div>
            </form>
        </div>
    </div>
</div>

<script src="js/bootstrap.js" type="text/javascript"></script>
<script src="js/jquery-2.2.4.js" type="text/javascript"></script>

</body>
</html>
