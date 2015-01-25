package cz.uhk.restaurace.dao.impl;

import java.util.List;

import cz.uhk.restaurace.dao.UserDao;
import cz.uhk.restaurace.model.Role;
import cz.uhk.restaurace.model.Shift;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cz.uhk.restaurace.model.User;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void addUser(User user) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(user);
	}

	@Override
	public void updateUser(User user) {
		user.setPasswordVerif(user.getPassword());
		Session session = this.sessionFactory.getCurrentSession();
		session.update(user);
	}

	@Override
	public User loadUser(String username) {
		return (User)sessionFactory.getCurrentSession().load(User.class, username);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<User> listUser() {
		Session session = this.sessionFactory.getCurrentSession();
		List<User> ul = session.createQuery("from User").list();
		return ul;
	}

	@Override
	public User getUserById(String username) {
		Session session = this.sessionFactory.getCurrentSession();
		User a = (User) session.get(User.class, username);
		return a;
	}

	@Override
	public void removeUser(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		User a = (User) session.load(User.class, new Integer(id));
		if (a != null) {
			session.delete(a);
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUsersByRole(Role.RoleType role) {
		Session session = this.sessionFactory.getCurrentSession();
		return session.createCriteria(User.class)
				.createCriteria("roles")
				.add(Restrictions.eq("roleType", role)).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
				.list();
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getEmployeesCurrentlyWorking(int hour, Shift.Day day, Role.RoleType role){
		Session session = this.sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(User.class, "user");
		criteria.createAlias("user.roles", "roles");
		criteria.createAlias("user.shifts", "shifts");
		criteria.add(Restrictions.eq("roles.roleType", role));
		criteria.add(Restrictions.eq("shifts.workDay", day));
		criteria.add(Restrictions.or(Restrictions.eq("shifts.sinceHour", hour),
						Restrictions.eq("shifts.toHour", hour - 1),
						Restrictions.and(Restrictions.lt("shifts.sinceHour", hour),
								Restrictions.gt("shifts.toHour", hour - 1)
						)
					)
				);
		List<User> list = criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return list;
	}
}
