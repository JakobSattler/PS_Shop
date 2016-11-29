/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojos;

import database.PasswordSecurity;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Jakob
 */
@Entity
public class Customer {

    @Id
    private String email;
    @Column(name = "pw_hash")
    private String pwHash;
    @Column(name = "pw_salt")
    private byte[] pwSalt;

    public Customer() {

    }

    /**
     *
     * @param email email which identifies the user
     * @param pwHash hash built of password and salt
     * @param pwSalt random salt array
     */
    public Customer(String email, String pwHash, byte[] pwSalt) {
        this.email = email;
        this.pwHash = pwHash;
        this.pwSalt = pwSalt;
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
}
