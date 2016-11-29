<%-- 
    Document   : home.jsp
    Created on : 29.11.2016, 14:49:12
    Author     : Jakob
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Herzlich willkommen beim P&S Online-Shop</h1>
        <form name="loginForm" method="POST" target="HomeServlet">
            Email: <input type="text" name="mail"/><br>
            Passwort: <input type="text" name="password"/><br>
            <input type="submit" value="Login"/>
        </form>
    </body>
</html>
