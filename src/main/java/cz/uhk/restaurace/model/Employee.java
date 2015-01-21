package cz.uhk.restaurace.model;

import javax.persistence.*;

/**
 * Created by dann on 20.12.2014.
 */

@Entity
public class Employee {

    @Id
    private String username;
    private transient EmployeeLoc employeeLoc;
    @Column(unique = true)
    private String picture;

    public Employee() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public EmployeeLoc getEmployeeLoc() {
        return employeeLoc;
    }

    public void setEmployeeLoc(EmployeeLoc employeeLoc) {
        this.employeeLoc = employeeLoc;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
