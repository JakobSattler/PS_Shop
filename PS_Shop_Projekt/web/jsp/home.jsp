<%-- 
    Document   : home.jsp
    Created on : 29.11.2016, 14:49:12
    Author     : Jakob
--%>
<%@page import="java.util.List"%>
<%@page import="pojos.Article"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ include file="../html/index.jsp" %> 

<!--        <form name="loginForm" method="POST" target="HomeServlet">
            Email: <input type="text" name="mail"/><br>
            Passwort: <input type="text" name="password"/><br>
            <input type="submit" value="Login"/>
            <input type="button" value="Registrieren"  onClick="window.location.href = 'RegisterPageServlet'">
        </form>-->
<%

%>

<table class="table table" style="background: #262626;color:white;">
    <%     List<Article> liste = (List) request.getAttribute("ArticleListe");
        for (Article a : liste)
        {
            out.println("<tr><td><h1>Name " + a.getName() + "</h1></td></tr><tr><td>Description: " + a.getDescription() + "</td></tr><tr><td>Sex: " + a.getSex() + "</td></tr>");
        }

    %>

</table>
</body>
</html>
