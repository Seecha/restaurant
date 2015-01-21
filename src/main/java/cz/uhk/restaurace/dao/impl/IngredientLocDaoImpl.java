package cz.uhk.restaurace.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cz.uhk.restaurace.dao.IngredientLocDao;
import cz.uhk.restaurace.model.IngredientLoc;
@Repository
public class IngredientLocDaoImpl implements IngredientLocDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void addIngredientLoc(IngredientLoc ingredientLoc) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(ingredientLoc);
		

	}

	@Override
	public void updateIngredientLoc(IngredientLoc ingredientLoc) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(ingredientLoc);

	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<IngredientLoc> listIngredientLoc(String lang) {
		Session session = this.sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(IngredientLoc.class);
		Criterion criterion = Restrictions.eq("language", lang);
		List<IngredientLoc> ingredientLocList = criteria.add(criterion).list();
		return ingredientLocList;
	}

	@Override
	public IngredientLoc getIngredientLocById(int id, String language) {
	
		Session session = this.sessionFactory.getCurrentSession();
	
		Criteria criteria = session.createCriteria(IngredientLoc.class);
		Criterion criterion = Restrictions.conjunction().add(Restrictions.eq("id", id)).
				add(Restrictions.eq("language", language));
		return (IngredientLoc) criteria.add(criterion).uniqueResult();
	}

	@Override
	public void removeIngredientLoc(int id, String language) {
		Session session = this.sessionFactory.getCurrentSession();
		String hql = "delete from IngredientLoc where language = :language and id = :id";
		session.createQuery(hql).setString("language", language).setInteger("id", id).executeUpdate();
	}

	@Override
	public void removeIngredientsLoc(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		String hql = "delete from IngredientLoc where id = :id";
		session.createQuery(hql).setInteger("id", id).executeUpdate();
		
		
	}

}
