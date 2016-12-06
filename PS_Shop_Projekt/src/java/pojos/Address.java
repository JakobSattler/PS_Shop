/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojos;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Jakob
 */
@Entity
public class Address implements Serializable {
    
    @Column(name = "address_id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int addressID;
    private String street;
    private String city;
    private int zipcode;
    
    @OneToMany(mappedBy = "address")
    private List<Customer> customers = new LinkedList<>();
    
    public Address() {
        
    }
    
    public Address(String street, String city, int zipcode) {
        this.street = street;
        this.city = city;
        this.zipcode = zipcode;
    }
    
    public int getAddressID() {
        return addressID;
    }
    
    public void setAddressID(int addressID) {
        this.addressID = addressID;
    }
    
    public String getStreet() {
        return street;
    }
    
    public void setStreet(String street) {
        this.street = street;
    }
    
    public String getCity() {
        return city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    
    public int getZipcode() {
        return zipcode;
    }
    
    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }
    
    public List<Customer> getCustomers() {
        return customers;
    }
    
    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }
    
    public void addCustomer(Customer c) {
        customers.add(c);
        c.setAddress(this);
    }
}
