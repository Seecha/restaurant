package cz.uhk.restaurace.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by dann on 21.12.2014.
 */

@Entity(name = "temp_customer_info")
public class TempCustomerInfo {

    @Id
    private int id;
    private String street;
    private String city;
    private String phonenumber;

    public TempCustomerInfo() {
    }

    public TempCustomerInfo(String street, String city, String phonenumber) {
        this.street = street;
        this.city = city;
        this.phonenumber = phonenumber;
    }

    public TempCustomerInfo(int id, String street, String city, String phonenumber) {
        this.id = id;
        this.street = street;
        this.city = city;
        this.phonenumber = phonenumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }
}
