package cz.uhk.restaurace.dao.impl;

import java.util.List;

import cz.uhk.restaurace.dao.DinnerTableDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import cz.uhk.restaurace.model.DinnerTable;

@Repository
public class DinnerTableDaoImpl implements DinnerTableDao {

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void addTable(DinnerTable dinnerTable) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(dinnerTable);

	}

	@Override
	public void updateTable(DinnerTable dinnerTable) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(dinnerTable);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DinnerTable> listTable() {
		Session session = this.sessionFactory.getCurrentSession();
		List<DinnerTable> sl = session.createQuery("from DinnerTable").list();
		return sl;
	}

	@Override
	public DinnerTable getTableById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		DinnerTable a = (DinnerTable) session.load(DinnerTable.class, new Integer(id));
		return a;
	}

	@Override
	public void removeTable(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		DinnerTable a = (DinnerTable) session.load(DinnerTable.class, new Integer(id));
		if (a != null) {
			session.delete(a);
		}

	}

}
