/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojos;

import database.PasswordSecurity;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Jakob
 */
@Entity
public class Customer implements Serializable {

    @Id
    private String email;
    private String firstName;
    private String lastName;
    @Column(name = "pw_hash")
    private String pwHash;
    @Column(name = "pw_salt")
    private byte[] pwSalt;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    public Customer() {

    }

    /**
     *
     * @param email email which identifies the user
     * @param password password in plain text
     * @param firstName
     * @param lastName
     * @param address
     * @throws java.security.NoSuchAlgorithmException
     */
    public Customer(String email, String password, String firstName, String lastName, Address address) throws NoSuchAlgorithmException {
        this.email = email;
        byte[] salt = PasswordSecurity.createSalt();
        this.pwSalt = salt;
        this.pwHash = PasswordSecurity.createMD5HasshWithSalt(password, salt);
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwHash() {
        return pwHash;
    }

    public void setPwHash(String pwHash) {
        this.pwHash = pwHash;
    }

    public byte[] getPwSalt() {
        return pwSalt;
    }

    public void setPwSalt(byte[] pwSalt) {
        this.pwSalt = pwSalt;
    }

    public String getVorname() {
        return firstName;
    }

    public void setVorname(String vorname) {
        this.firstName = vorname;
    }

    public String getNachname() {
        return lastName;
    }

    public void setNachname(String nachname) {
        this.lastName = nachname;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
