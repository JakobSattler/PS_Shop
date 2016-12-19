<%-- 
    Document   : home.jsp
    Created on : 29.11.2016, 14:49:12
    Author     : Jakob
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="../html/index.jsp" %> 

    <form name="loginForm" method="POST" target="#">
        Email: <input type="text" name="mail"/><br>
        Passwort: <input type="text" name="password"/><br>
        <input type="submit" value="Login"/>
    </form>
</body>
</html>
