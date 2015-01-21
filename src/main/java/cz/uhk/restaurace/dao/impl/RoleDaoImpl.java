package cz.uhk.restaurace.dao.impl;

import java.util.List;

import cz.uhk.restaurace.dao.RoleDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import cz.uhk.restaurace.model.Role;

@Repository
public class RoleDaoImpl implements RoleDao {

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void addRole(Role role) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(role);

	}

	@Override
	public void updateRole(Role role) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(role);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Role> listRole() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Role> rl = session.createQuery("from Role").list();
		return rl;
	}

	@Override
	public Role getRoleById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Role a = (Role) session.load(Role.class, new Integer(id));
		return a;
	}

	@Override
	public void removeRole(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Role a = (Role) session.load(Role.class, new Integer(id));
		if (a != null) {
			session.delete(a);
		}

	}

}
