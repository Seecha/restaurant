package cz.uhk.restaurace.dao.impl;

import java.util.List;

import cz.uhk.restaurace.dao.ShiftDao;
import cz.uhk.restaurace.model.User;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cz.uhk.restaurace.model.Shift;

@Repository
public class ShiftDaoImpl implements ShiftDao {

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void addShift(Shift shift) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(shift);

	}

	@Override
	public void updateShift(Shift shift) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(shift);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Shift> listShift() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Shift> sl = session.createQuery("from Shift").list();
		return sl;
	}

	@Override
	public Shift getShiftById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Shift a = (Shift) session.get(Shift.class, new Integer(id));
		return a;
	}

	@Override
	public void removeShift(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Shift a = (Shift) session.load(Shift.class, new Integer(id));
		if (a != null) {
			session.delete(a);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Shift getCurrentEmployeesShift(int hour, Shift.Day day, User user) {
		Session session = this.sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Shift.class, "shift");
		criteria.add(Restrictions.eq("workDay", day));
		criteria.add(Restrictions.or(Restrictions.eq("sinceHour", hour),
						Restrictions.eq("toHour", hour - 1),
						Restrictions.and(Restrictions.lt("sinceHour", hour),
								Restrictions.gt("toHour", hour - 1)
						)
				)
		);
		criteria.createCriteria("employees").add(Restrictions.eq("username", user.getUsername()));
		List<Shift> shifts = criteria.list();
		return shifts.get(0);
	}
}
