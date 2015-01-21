package cz.uhk.restaurace.dao;

import cz.uhk.restaurace.model.Delivery;

import java.util.List;

/**
 * Created by dann on 22.11.2014.
 */


public interface DeliveryDao {
    public void addDelivery(Delivery delivery);
    public void updateDelivery(Delivery delivery);
    public List<Delivery> listDelivery();
    public Delivery getDeliveryById(String name);
    public void removeDelivery(int id);
    public Delivery loadDeliveryById(String name);
}
