<#macro page>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
    <title>Textile</title>

    <!-- Bootstrap CSS -->
    <link type="text/css" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous"/>
    <link type="text/css" rel="stylesheet" href="/static/css/main.css"/>

    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>

    <script type="text/javascript" src="https://www.google.com/recaptcha/api.js" ></script>
    <link type="text/css" rel="stylesheet" href="/static/js/node_modules/air-datepicker/air-datepicker.css" />
    <script type="text/javascript" src="/static/js/node_modules/air-datepicker/air-datepicker.js"></script>
    <#--async defer-->
    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
</head>
<body>
<div class="container">
    <#include "navbar.ftlh">
</div>

<div class="container mt-2">
<#nested>
</div>

<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous"></script>
<script type="text/javascript" src="/static/js/node_modules/air-datepicker/my_script_to_air.js"></script>
<script type="text/javascript" src="/static/js/app.js"></script>
</body>
</html>
</#macro>