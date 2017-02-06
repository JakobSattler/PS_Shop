/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import database.DBAccess;
import database.PasswordSecurity;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import pojos.Article;
import pojos.Customer;

/**
 *
 * @author Jakob
 */
@WebServlet(name = "HomeServlet", urlPatterns = {"/HomeServlet"})
public class HomeServlet extends HttpServlet {

    private DBAccess dba;

    @PersistenceUnit
    private EntityManagerFactory emf;

    @Resource
    private UserTransaction utx;

    private void handleLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, NotSupportedException, SystemException, RollbackException, HeuristicRollbackException, SecurityException, IllegalStateException, HeuristicMixedException, NoSuchAlgorithmException, ServletException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        boolean loginCorrect;

        if (dba.isLoginCorrect(email, password)) {
            Customer customer = dba.findCustomerByEmail(email);
            String sessionID = customer.getPwHash();
            request.getSession().setAttribute("customer", customer);
//                                request.getSession().setAttribute("pemrissions", dba.getPermissionsByUser(user));
            request.getSession().setAttribute("sessionID", sessionID);
            System.out.println("output - correct login");
            Cookie cookie = new Cookie("sessionID", customer.getPwHash());
            cookie.setMaxAge(Integer.MAX_VALUE);
            response.addCookie(cookie);
            response.sendRedirect("HomeServlet");
        } else {
            request.setAttribute("loginError", "Benutzer nicht vorhanden oder Passwort nicht korrekt!");
            request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
        }
    }

    public boolean isUserLoggedIn(HttpServletRequest request) {
        boolean loggedIn = false;
        Cookie[] cookies = request.getCookies();
        Cookie sessionIDCookie = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("sessionID")) {
                    sessionIDCookie = cookie;
                }
            }
        }
        String sessionID = (String) request.getSession().getAttribute("sessionID");
        if (sessionIDCookie != null && sessionID != null) {
            loggedIn = sessionIDCookie.getValue().equals(sessionID);
        }
        return loggedIn;
    }

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("hallo");
            try {
                dba = DBAccess.getInstance(emf, utx);
                List<Article> liste = dba.getAllArticles();
                request.setAttribute("ArticleListe", liste);
            } catch (NotSupportedException ex) {
                Logger.getLogger(HomeServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SystemException ex) {
                Logger.getLogger(HomeServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            RequestDispatcher req = request.getRequestDispatcher("/jsp/home.jsp");
            req.forward(request, response);
            /* TODO output your page here. You may use following sample code. */
//            request.getRequestDispatcher("/jsp/home.jsp").forward(request, response);
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
            throws ServletException, IOException {
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
            throws ServletException, IOException {
        if (request.getParameter("login").equals("login")) {
            try {
                handleLogin(request, response);
            } catch (NotSupportedException ex) {
                Logger.getLogger(HomeServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SystemException ex) {
                Logger.getLogger(HomeServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (RollbackException ex) {
                Logger.getLogger(HomeServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (HeuristicRollbackException ex) {
                Logger.getLogger(HomeServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SecurityException ex) {
                Logger.getLogger(HomeServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalStateException ex) {
                Logger.getLogger(HomeServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (HeuristicMixedException ex) {
                Logger.getLogger(HomeServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(HomeServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            processRequest(request, response);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    @Override
    public void init(ServletConfig config) throws ServletException {
        try {
            dba = DBAccess.getInstance(emf, utx);

            dba.saveCustomer("test3", "test3", "Jakob", "Sattler", null);
            dba.saveCustomer("test1", "test1", null, null, null);
            dba.saveCustomer("test2", "test2", null, null, null);
            dba.insertArticle(new Article("Hose", "tolle Hose", 'M',"Hugo Boss","pic1"));
            dba.insertArticle(new Article("Shirt", "tolle Shirt", 'W',"Nike","pic1"));
            dba.insertArticle(new Article("Pulli", "tolle Pulli", 'M',"Hugo Boss","pic1"));

        } catch (NotSupportedException ex) {
            Logger.getLogger(HomeServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SystemException ex) {
            Logger.getLogger(HomeServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(HomeServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RollbackException ex) {
            Logger.getLogger(HomeServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HeuristicMixedException ex) {
            Logger.getLogger(HomeServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HeuristicRollbackException ex) {
            Logger.getLogger(HomeServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
