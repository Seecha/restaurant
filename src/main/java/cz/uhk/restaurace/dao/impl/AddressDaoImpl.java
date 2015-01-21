package cz.uhk.restaurace.dao.impl;

import java.util.List;

import cz.uhk.restaurace.dao.AddressDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cz.uhk.restaurace.model.Address;

@Repository
public class AddressDaoImpl implements AddressDao {

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void addAddress(Address address) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(address);
		
		
	}

	@Override
	public void updateAddress(Address address) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(address);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Address> listAddress() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Address> addressList = session.createQuery("from Address").list();
		return addressList;
	}

	@Override
	public Address getAddressById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Address a = (Address)session.get(Address.class, id);
		return a;
	}

	@Override
	public void removeAddress(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Address a = (Address) session.load(Address.class, new Integer(id));
		if (a != null) {
			session.delete(a);
		}
		
	}

}
