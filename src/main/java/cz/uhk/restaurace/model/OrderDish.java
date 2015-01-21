package cz.uhk.restaurace.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by dann on 25.12.2014.
 */

@Entity
@Table(name = "ordered_dishes")
@AssociationOverrides({
        @AssociationOverride(name = "pk.dish", joinColumns = @JoinColumn(name = "dish_id")),
        @AssociationOverride(name = "pk.order", joinColumns = @JoinColumn(name = "order_id"))})
public class OrderDish {

    @EmbeddedId
    private OrderDishId pk = new OrderDishId();
    private int amount;

    public OrderDish() {
    }

    public OrderDish(CustomerOrder order, DishGeneral dish, int amount) {
        this.pk.order = order;
        this.pk.dish = dish;
        this.amount = amount;
    }

    public OrderDishId getPk() {
        return pk;
    }

    public void setPk(OrderDishId pk) {
        this.pk = pk;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setOrder(CustomerOrder order){
        this.pk.setOrder(order);
    }

    @Transient
    public CustomerOrder getOrder(CustomerOrder order){
        return this.pk.getOrder();
    }

    public void setDish(DishGeneral dish){
        this.pk.setDish(dish);
    }

    @Transient
    public DishGeneral getDish(){
        return this.pk.getDish();
    }

    @Embeddable
    public static class OrderDishId implements Serializable{

        public static final long serialVersionUID = 1L;

        @ManyToOne
        private CustomerOrder order;
        @ManyToOne
        private DishGeneral dish;
        
        public OrderDishId() {
		}
        public CustomerOrder getOrder() {
            return order;
        }

        public void setOrder(CustomerOrder order) {
            this.order = order;
        }

        public DishGeneral getDish() {
            return dish;
        }

        public void setDish(DishGeneral dish) {
            this.dish = dish;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            OrderDishId that = (OrderDishId) o;

            if (!dish.equals(that.dish)) return false;
            if (!order.equals(that.order)) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = order.hashCode();
            result = 31 * result + dish.hashCode();
            return result;
        }
    }
}
