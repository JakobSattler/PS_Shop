/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.security.NoSuchAlgorithmException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
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
    public void saveCustomer(String email, String password, String firstName, String lastName, Address address) throws NoSuchAlgorithmException, NotSupportedException, SystemException, RollbackException, HeuristicMixedException, HeuristicRollbackException {
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

    public boolean insertArticle(Article article) throws NotSupportedException, SystemException, RollbackException, HeuristicMixedException, HeuristicRollbackException
    {
        utx.begin();
        EntityManager em = emf.createEntityManager();
        em.persist(article);

        utx.commit();
        em.close();
        return true;
    }
}
