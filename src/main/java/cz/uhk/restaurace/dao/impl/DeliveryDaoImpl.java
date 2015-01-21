package cz.uhk.restaurace.dao.impl;

import cz.uhk.restaurace.dao.DeliveryDao;
import cz.uhk.restaurace.model.Delivery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by dann on 22.11.2014.
 */

@Repository
public class DeliveryDaoImpl implements DeliveryDao{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addDelivery(Delivery delivery) {
        sessionFactory.getCurrentSession().persist(delivery);
    }

    @Override
    public void updateDelivery(Delivery delivery) {
        sessionFactory.getCurrentSession().update(delivery);
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<Delivery> listDelivery() {
        return sessionFactory.getCurrentSession().createQuery("FROM Delivery").list();
    }

    @Override
    public Delivery getDeliveryById(String name) {
        return (Delivery)sessionFactory.getCurrentSession().get(Delivery.class, name);
    }

    @Override
    public void removeDelivery(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(session.get(Delivery.class, id));
    }

    @Override
    public Delivery loadDeliveryById(String name) {
        return (Delivery)this.sessionFactory.getCurrentSession().load(Delivery.class, name);
    }
}
