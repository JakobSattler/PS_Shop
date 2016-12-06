/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojos;

import java.io.Serializable;
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
    private String vorname;
    private String nachname;
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

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
