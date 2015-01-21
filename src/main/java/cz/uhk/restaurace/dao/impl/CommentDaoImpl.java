package cz.uhk.restaurace.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cz.uhk.restaurace.dao.CommentDao;
import cz.uhk.restaurace.model.Comment;
import cz.uhk.restaurace.model.DishGeneral;

@Repository
public class CommentDaoImpl implements CommentDao{
	
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void addComment(Comment comment) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(comment);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Comment> getCommentsByDish(DishGeneral dish) {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Comment.class);
		return criteria.add(Restrictions.eq("dish", dish)).list();
	}

	@Override
	public void removeComment(int id) {
		
		Session session = this.sessionFactory.getCurrentSession();
		Comment a = (Comment) session.get(Comment.class, new Integer(id));
		if (a != null) {
			session.delete(a);
		}
		
	}

}
