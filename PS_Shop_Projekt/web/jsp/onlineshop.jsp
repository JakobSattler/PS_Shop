<%-- 
    Document   : online_shop
    Created on : 19.12.2016, 14:59:23
    Author     : dinop
--%>

<%@page import="java.io.File"%>
<%@page import="java.util.LinkedList"%>
<%@page import="javax.transaction.UserTransaction"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@page import="database.DBAccess"%>
<%@page import="java.util.List"%>
<%@page import="pojos.Article"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="../html/index.jsp" %> 

<form name="searchField" method="post">
    <input type="text" name="filter"/><br>
    <input type="submit" value="Suche"/>
</form>
<%!    private EntityManagerFactory emf;
    private UserTransaction utx;
    private DBAccess dba;
    private final String PATH = System.getProperty("user.dir") + File.separator + "src" + File.separator + "res" + File.separator;
%>


<table class="table table" style="background: #262626;color:white;">
    <%
        try
        {
            dba = DBAccess.getInstance(emf, utx);
            List<Article> liste = new LinkedList<Article>();
            System.out.println(request.getParameter("filter").trim());
            if (request.getParameter("filter").trim().equals("") || request.getParameter("filter") == null)
            {
                System.out.println("kein filter");
                liste.clear();
                liste.addAll(dba.getAllArticles());

            } else
            {
                System.out.println("filter");
                liste.clear();

                liste.addAll(dba.getArticleByName(request.getParameter("filter").trim()));
            }

            for (Article a : liste)
            {

                out.println("<tr><td><img src='" + PATH + a.getPic() + "png" + "' alt='" + a.getPic() + "' width='200' height='200'></tr></td><tr><td>Name " + a.getName() + "</td></tr><tr><td>Brand: " + a.getBrand() + "</td></tr><tr><td>Description: " + a.getDescription() + "</td></tr><tr><td>Sex: " + a.getSex() + "</td></tr>");
            }
        } catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    %>
</table>
</body>
</html>
