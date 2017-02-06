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
    <input type="radio" name="gender" value="M"> Male<br>
    <input type="radio" name="gender" value="W"> Female<br>
    <input type="radio" name="gender" value="A" checked="checked">All<br>
    <select name="brands">
        <option  value="A">All Brands</option>
        <option  value="Hugo Boss">Hugo Boss</option>
        <option  value="Nike">Nike</option>
        <option  value="Guess">Guess</option>
        <option value="Elias Rumelis Jeans">Elias Rumelis Jeans</option>
    </select>
    <input type="submit" value="Suche"/>
</form>
<%!    private EntityManagerFactory emf;
    private UserTransaction utx;
    private DBAccess dba;
%>


<table class="table table" style="background: #262626;color:white;">
    <%
        String path = this.getServletContext().getContextPath();
        try
        {
            dba = DBAccess.getInstance(emf, utx);
            List<Article> liste = new LinkedList<Article>();
            //if (request.getParameter("filter").trim().equals("") || request.getParameter("filter") == null )
           // {
               // System.out.println("kein filter");
                //liste.clear();
                //liste.addAll(dba.getAllArticles());

           // } 
          //  else
          //  {
                liste.clear();

                //liste.addAll(dba.getArticleByName(request.getParameter("filter").trim()));
                
                liste.addAll(dba.getArticleByFilter(request.getParameter("filter").trim(), request.getParameter("brands"),request.getParameter("gender")));
          //  }

            for (Article a : liste)
            {

                out.println("<tr><td><img src='" + path + a.getPic() + ".png" + "' alt='" + a.getPic() + "' width='200' height='200'></tr></td><tr><td>Name " + a.getName() + "</td></tr><tr><td>Brand: " + a.getBrand() + "</td></tr><tr><td>Description: " + a.getDescription() + "</td></tr><tr><td>Sex: " + a.getSex() + "</td></tr>");
            }
        } catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    %>
</table>
</body>
</html>
