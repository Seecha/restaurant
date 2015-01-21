package cz.uhk.restaurace.dao.impl;

import cz.uhk.restaurace.dao.EmployeeLocDao;
import cz.uhk.restaurace.model.EmployeeLoc;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by dann on 20.12.2014.
 */

@Repository
public class EmployeeLocDaoImpl implements EmployeeLocDao {


    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addEmployeeLoc(EmployeeLoc employee) {
        sessionFactory.getCurrentSession().persist(employee);
    }

    @Override
    public void updateEmployeeLoc(EmployeeLoc employee) {
        sessionFactory.getCurrentSession().update(employee);
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<EmployeeLoc> getAllEmployeesLocs(String language) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(EmployeeLoc.class);
        criteria.add(Restrictions.eq("language", language));
        return criteria.list();
    }

    @Override
    public EmployeeLoc getEmployeeLocById(String language, String username) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(EmployeeLoc.class);
        return  (EmployeeLoc) criteria.add(Restrictions.eq("language", language))
                .add(Restrictions.eq("username", username))
                .uniqueResult();
    }

    @Override
    public void removeEmployeeLoc(String language, String username) {
        Session session = this.sessionFactory.getCurrentSession();
        String hql = "delete from IngredientLoc where language = :language and id = :id";
        session.createQuery(hql).setString("language", language).setString("id", username).executeUpdate();
    }
}
