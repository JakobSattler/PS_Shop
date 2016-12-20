<%-- 
    Document   : login
    Created on : 19.12.2016, 15:00:04
    Author     : dinop
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="../html/index.jsp" %> 

<html>
    <head>
        <title>Login</title>
    </head>
    <body>
        <form name="loginForm" method="POST" action="HomeServlet">
            <div style="width: 25%">
                <table class="contactTable">
                    <tr>
                        <td>
                            Email: 
                        </td>
                        <td>
                            <input style="width: 100%" type="text" name="email"/><br>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Passwort: 
                        </td>
                        <td>
                            <input style="width: 100%" type="text" name="password"/>
                        </td>
                    </tr>
                    <%
                        if (request.getAttribute("loginError") != null) {
                    %>
                    <tr>
                        <td>
                            <label ><%= request.getAttribute("loginError")%></label>
                        </td>
                    <br>
                    <%
                        }
                    %>
                    <tr>
                        <td>
                            <input style="width: 100%" type="submit" value="Login"/>
                        </td>
                        <td>
                            <input style="width: 100%" type="button" value="Registrieren"  onClick="window.location.href = 'RegisterPageServlet'"/>
                        </td>
                    </tr>
                </table>
            </div>
            <input type="hidden" value="login" name="login"/>
        </form>
    </body>
</html>
