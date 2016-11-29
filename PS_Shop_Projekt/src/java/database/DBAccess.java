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
import pojos.Customer;

/**
 *
 * @author Jakob
 */
public class DBAccess {

    private static DBAccess theInstance = null;

    public static DBAccess getInstance(EntityManagerFactory emf, UserTransaction utx) throws NotSupportedException, SystemException {
        if (theInstance == null) {
            theInstance = new DBAccess(emf, utx);
        }
        return theInstance;
    }

    private EntityManagerFactory emf;
    private UserTransaction utx;

    private DBAccess(EntityManagerFactory emf, UserTransaction utx) throws NotSupportedException, SystemException {
        this.emf = emf;
        this.utx = utx;
        assert this.emf != null;
    }

    /**
     *
     * @param email
     * @param password password in plain text
     */
    public void saveCustomer(String email, String password) throws NoSuchAlgorithmException, NotSupportedException, SystemException, RollbackException, HeuristicMixedException, HeuristicRollbackException {
        byte[] salt = PasswordSecurity.createSalt();
        String pwHash = PasswordSecurity.createMD5HasshWithSalt(password, salt);
        Customer c = new Customer(email, pwHash, salt);

        utx.begin();
        EntityManager em = emf.createEntityManager();
        em.persist(c);
        utx.commit();
        em.close();
    }
    
    public static void main(String[] args) throws NoSuchAlgorithmException {
        byte[] salt = PasswordSecurity.createSalt();
        System.out.println(PasswordSecurity.createMD5HasshWithSalt("hallo", salt));
    }
}