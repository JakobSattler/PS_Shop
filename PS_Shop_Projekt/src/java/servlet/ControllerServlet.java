/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import database.DBAccess;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import pojos.Article;

/**
 *
 * @author dinop
 */
@WebServlet(name = "ControllerServlet", urlPatterns
        =
        {
            "/ControllerServlet"
        })

public class ControllerServlet extends HttpServlet {

    @PersistenceUnit
    private EntityManagerFactory emf;

    @Resource
    private UserTransaction utx;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        String menuParam = request.getParameter("menu");
        if (menuParam.equals("logout"))
        {
            handleLogout(request, response);
        } else
        {

            System.out.println("basdfkalsödfjlkjadshfkhaskl");
            RequestDispatcher req;
            System.out.println(request.getQueryString());

//                if (menuParam.equals("onlineshop"))
//                {
//                    DBAccess dba = DBAccess.getInstance(emf, utx);
//                    List<Article> liste = dba.getAllArticles();
//                    request.setAttribute("ArticleListe", liste);
//                }
            req = request.getRequestDispatcher("/jsp/" + menuParam + ".jsp");
            req.forward(request, response);

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo()
    {
        return "Short description";
    }// </editor-fold>

    private void handleLogout(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies)
        {
            if (cookie.getName().equals("sessionID"))
            {
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }
        request.getSession().setAttribute("customer", null);
        request.getSession().setAttribute("sessionID", null);
        response.sendRedirect("HomeServlet");
    }

}
