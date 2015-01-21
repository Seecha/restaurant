package cz.uhk.restaurace.dao.impl;

import java.util.List;

import cz.uhk.restaurace.dao.CustomerOrderDao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cz.uhk.restaurace.model.CustomerOrder;


@Repository
public class CustomerOrderDaoImpl implements CustomerOrderDao {

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void addOrder(CustomerOrder customerOrder) {
		Session session = this.sessionFactory.getCurrentSession();
		session.saveOrUpdate(customerOrder);
		
	}

	@Override
	public void updateOrder(CustomerOrder customerOrder) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(customerOrder);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CustomerOrder> listUserOrder(String username) {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(CustomerOrder.class);
		List<CustomerOrder> orders = criteria.add(Restrictions.eq("customer.username", username)).list();
		return orders;
	}

	@Override
	public CustomerOrder getOrderById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		CustomerOrder a = (CustomerOrder) session.get(CustomerOrder.class, new Integer(id));
		return a;
	}

	@Override
	public void removeOrder(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		CustomerOrder a = (CustomerOrder) session.load(CustomerOrder.class, new Integer(id));
		if (a != null) {
			session.delete(a);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CustomerOrder> getUnprocessedRegisteredCustomerOrders() {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(CustomerOrder.class);
		return criteria.add(Restrictions.eq("processed", false))
				.add(Restrictions.isNotNull("customer"))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
				.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CustomerOrder> getUnprocessedNotregisteredCustomerOrders() {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(CustomerOrder.class);
		return criteria.add(Restrictions.eq("processed", false))
				.add(Restrictions.isNull("customer"))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
				.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CustomerOrder> getProcessesRegisteredCustomerOrder() {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(CustomerOrder.class);
		return criteria.add(Restrictions.eq("processed", true))
				.add(Restrictions.isNotNull("customer"))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
				.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CustomerOrder> getProcessesNotregisteredCustomerOrder() {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(CustomerOrder.class);
		return criteria.add(Restrictions.eq("processed", true))
				.add(Restrictions.isNull("customer"))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
				.list();
	}
}
