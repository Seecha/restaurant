package cz.uhk.restaurace.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.util.Collection;

/**
 * Created by dann on 22.11.2014.
 */

@Entity
public class Delivery {

    @Id
    private String name;
    private BigDecimal price;
    private String companyName;
    private String telephone;
    private transient Boolean active;
    @OneToMany(mappedBy = "delivery")
    private Collection<CustomerOrder> customerOrders;

    public Delivery() {
    }

    public Delivery(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Collection<CustomerOrder> getCustomerOrders() {
        return customerOrders;
    }

    public void setCustomerOrders(Collection<CustomerOrder> customerOrders) {
        this.customerOrders = customerOrders;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
