<%-- 
    Document   : index
    Created on : 19.12.2016, 15:46:30
    Author     : dinop
--%>

<%@page import="pojos.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang=''>
    <head>
        <meta charset='utf-8'>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>
        <script src="https://s3.amazonaws.com/menumaker/menumaker.min.js" type="text/javascript"></script>
        <script src="js/script.js"></script>
        <!--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" >-->
        <link rel="stylesheet" href="css/styles.css">
        <link rel="stylesheet" href="css/homestyle.css">
        
    </head>
    <body>
        <%!
            private boolean loggedIn;
            private Customer c;
        %>
        <%
            c = (Customer) session.getAttribute("customer");
            loggedIn = c != null;
//            System.out.println(c);
//            System.out.println(session.getAttribute("loggedIn"));
//            if (session.getAttribute("loggedIn").equals("true")) {
//                loggedIn = true;
//            }
        %>
        <div id="cssmenu">
            <ul>
                <li><a href="#" target="_blank"><i class="fa fa-fw fa-home"></i> Home</a></li>
                <li><a href="ControllerServlet?menu=news"><i class="fa fa-fw fa-info"></i> News</a></li>
                <li><a href="ControllerServlet?menu=brands"><i class="fa fa-fw fa-copyright"></i> Marken</a></li>
                <li><a href="ControllerServlet?menu=onlineshop"><i class="fa fa-fw fa-globe"></i> Online-Shop</a></li>
                <li><a href="ControllerServlet?menu=shoppingcart"><i class="fa fa-fw fa-shopping-cart"></i> Warenkorb</a></li>
                <li><a href="ControllerServlet?menu=contact"><i class="fa fa-fw fa-phone"></i> Kontakt</a></li>
                    <% if (!loggedIn) { %>
                <li><a href="ControllerServlet?menu=login"><i class="fa fa-fw fa-lock"></i> Login</a></li>
                <li><a href="ControllerServlet?menu=register"><i class="fa fa-fw"></i>Registrieren</a></li>
                    <% } else {%>
                <li><a href="#"><i class="fa fa-fw"></i>Eingeloggt als <%=c.getVorname() + " " + c.getNachname()%></a></li>
                <li><a href="ControllerServlet?menu=logout"><i class="fa fa-fw fa-lock"></i> Logout</a></li>
                    <% }%>
            </ul>
        </div>


