/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import pojos.Address;
import pojos.Article;
import pojos.Customer;

/**
 *
 * @author Jakob
 */
public class DBAccess {

    private static DBAccess theInstance = null;

    public static DBAccess getInstance(EntityManagerFactory emf, UserTransaction utx) throws NotSupportedException, SystemException
    {
        if (theInstance == null)
        {
            theInstance = new DBAccess(emf, utx);
        }
        return theInstance;
    }

    private EntityManagerFactory emf;
    private UserTransaction utx;

    private DBAccess(EntityManagerFactory emf, UserTransaction utx) throws NotSupportedException, SystemException
    {
        this.emf = emf;
        this.utx = utx;
        assert this.emf != null;
    }

    /**
     *
     * @param email
     * @param password password in plain text
     */
    public void saveCustomer(String email, String password, String firstName, String lastName, Address address) throws NoSuchAlgorithmException, NotSupportedException, SystemException, RollbackException, HeuristicMixedException, HeuristicRollbackException
    {
        Customer c = new Customer(email, password, firstName, lastName, address);

        utx.begin();
        EntityManager em = emf.createEntityManager();
        em.persist(c);
        utx.commit();
        em.close();
    }

    public Customer findCustomerByEmail(String email) throws NotSupportedException, SystemException, RollbackException, HeuristicRollbackException, SecurityException, IllegalStateException, HeuristicMixedException
    {
        utx.begin();
        EntityManager em = emf.createEntityManager();
        Customer c = (Customer) em.find(Customer.class, email);
        utx.commit();
        em.close();
        return c;
    }

    public boolean userExists(String email) throws NotSupportedException, SystemException, RollbackException, HeuristicRollbackException, SecurityException, IllegalStateException, HeuristicMixedException
    {
        return findCustomerByEmail(email) != null;
    }

    public boolean isLoginCorrect(String email, String password) throws NotSupportedException, SystemException, RollbackException, HeuristicRollbackException, SecurityException, IllegalStateException, HeuristicMixedException, NoSuchAlgorithmException
    {
        if (findCustomerByEmail(email) == null)
        {
            return false;
        }
        return PasswordSecurity.isPasswordCorrect(password, findCustomerByEmail(email));
    }

    public boolean insertArticle(Article article) throws NotSupportedException, SystemException, RollbackException, HeuristicMixedException, HeuristicRollbackException
    {
        utx.begin();
        EntityManager em = emf.createEntityManager();
        em.persist(article);

        utx.commit();
        em.close();
        return true;
    }

    public List<Article> getAllArticles()
    {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Article> tq = em.createNamedQuery("Article.getAllArticle", Article.class);
        return tq.getResultList();
    }

    public List<Article> getArticleByName(String filter)
    {
        EntityManager em = emf.createEntityManager();
//        return em.createQuery("SELECT a FROM Article a WHERE a.name LIKE ('%?1%')").setParameter(1, filter).getResultList();
        return em.createQuery("SELECT a FROM Article a WHERE a.name LIKE ('%" + filter + "%')").getResultList();
    }

    public List<Article> getArticleByFilter(String name, String brand, String gender)
    {
        
        if (gender.toUpperCase().equals("A"))
        {
            gender = "";
        }

        if (brand.toUpperCase().equals("A"))
        {
            brand = "";
        }
        
        System.out.println(brand);
        System.out.println(gender);
        EntityManager em = emf.createEntityManager();
        return em.createQuery("SELECT a FROM Article a WHERE a.name LIKE ('%" + name + "%') AND a.brand LIKE ('%" + brand + "%') AND a.sex LIKE('%" + gender + "%')").getResultList();
    }
}
