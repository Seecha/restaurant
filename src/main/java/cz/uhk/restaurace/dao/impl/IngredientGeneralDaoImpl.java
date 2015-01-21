package cz.uhk.restaurace.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cz.uhk.restaurace.dao.IngredientGeneralDao;
import cz.uhk.restaurace.model.IngredientGeneral;
import cz.uhk.restaurace.model.IngredientGeneral.IngredientType;
@Repository
public class IngredientGeneralDaoImpl implements IngredientGeneralDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void addIngredientGeneral(IngredientGeneral ingredient) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(ingredient);

	}

	@Override
	public void updateIngredientGeneral(IngredientGeneral ingredient) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(ingredient);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<IngredientGeneral> listIngredientsGeneral() {
		Session session = this.sessionFactory.getCurrentSession();
		List<IngredientGeneral> il = session.createQuery("from IngredientGeneral").list();
		return il;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<IngredientGeneral> getIngredientsGeneralByCategory(
			IngredientType type) {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(IngredientGeneral.class);
		return criteria.add(Restrictions.eq("type", type)).list();
	}

	@Override
	public IngredientGeneral getIngredientGeneralById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		IngredientGeneral a = (IngredientGeneral) session.get(IngredientGeneral.class, new Integer(id));
		return a;
	}

	@Override
	public void removeIngredientGeneral(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		IngredientGeneral a = (IngredientGeneral) session.get(IngredientGeneral.class, new Integer(id));
		if (a != null) {
			session.delete(a);
		}
	}

	@Override
	public Map<Integer, IngredientGeneral> loadIngredientsGeneral(Map<Integer, IngredientGeneral> ingredients) {
		Session session = this.sessionFactory.getCurrentSession();
		Map<Integer, IngredientGeneral> ingrs = new HashMap<Integer, IngredientGeneral>();
		for(Map.Entry<Integer, IngredientGeneral> entry : ingredients.entrySet()){
			ingrs.put(entry.getKey(), (IngredientGeneral)session.load(IngredientGeneral.class, entry.getKey()));
		}
		return ingrs;
	}
}
