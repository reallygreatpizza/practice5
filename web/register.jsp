<%-- 
    Document   : register
    Created on : Nov 7, 2019, 1:50:49 PM
    Author     : fssco
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Register</h1>

        <form action="Public" method="POST">
            <input type="hidden" name="action" value="register">
            <label>email</label>
            <input type="text" name="email">
            <br>
            <label>firstname</label>
            <input type="text" name="firstName">
            <br>
            <label>lastname</label>
            <input type="text" name="lastName">
            <br>

            <label>userName</label>
            <input type="text" name="userName">
            <br>

            <label>password</label>
            <input type="text" name="password">

            <br>
            <input type="submit" value="register">
        </form>
    </body>
</html>
