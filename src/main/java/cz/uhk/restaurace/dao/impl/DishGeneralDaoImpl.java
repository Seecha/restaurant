package cz.uhk.restaurace.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cz.uhk.restaurace.dao.DishGeneralDao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cz.uhk.restaurace.model.DishGeneral;
@Repository
public class DishGeneralDaoImpl implements DishGeneralDao {

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Override
	public void addDishGeneral(DishGeneral dishGeneral) {
		Session session = this.sessionFactory.getCurrentSession();
		session.saveOrUpdate(dishGeneral);
		
	}

	@Override
	public void updateDishGeneral(DishGeneral dishGeneral) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(dishGeneral);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DishGeneral> listDishesGeneral() {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(DishGeneral.class);
		return criteria.add(Restrictions.not(Restrictions.eq("dishCategory", DishGeneral.DishCategory.DRINK)))
				.add(Restrictions.not(Restrictions.eq("dishCategory", DishGeneral.DishCategory.TEPPANYAKI)))
				.addOrder(Order.asc("id")).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DishGeneral> listDrinksGeneral() {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(DishGeneral.class);
		return criteria.add(Restrictions.eq("dishCategory", DishGeneral.DishCategory.DRINK))
				.addOrder(Order.asc("id")).list();
	}

	@Override
	public DishGeneral getDishGeneralById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		DishGeneral a = (DishGeneral) session.get(DishGeneral.class, id);
		return a;
	}

	@Override
	public void removeDishGeneral(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		DishGeneral a = (DishGeneral) session.load(DishGeneral.class, id);
		if (a != null) {
			session.delete(a);
		}
	}

	@Override
	public Map<Integer, DishGeneral> loadOrderedDishes(Map<Integer, DishGeneral> dishes) {
		Session session = this.sessionFactory.getCurrentSession();
		Map<Integer, DishGeneral> proxies = new HashMap<Integer, DishGeneral>();
		for(Map.Entry<Integer, DishGeneral> entry : dishes.entrySet()){
			DishGeneral proxy = (DishGeneral) session.load(DishGeneral.class, entry.getKey());
			proxies.put(entry.getKey(), proxy);
		}
		return proxies;
	}
}
