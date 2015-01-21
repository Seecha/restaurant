package cz.uhk.restaurace.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cz.uhk.restaurace.dao.CommentDao;
import cz.uhk.restaurace.model.Comment;
import cz.uhk.restaurace.model.DishGeneral;
import cz.uhk.restaurace.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	private CommentDao commentDao;
	
	public void setCommentDao(CommentDao commentDao) {
		this.commentDao = commentDao;
	}

	@Transactional
	@Override
	public void addComment(Comment comment) {
		commentDao.addComment(comment);
		
	}

	@Transactional
	@Override
	public List<Comment> getCommentsByDish(DishGeneral dish) {
		return commentDao.getCommentsByDish(dish);
	}

	@Transactional
	@Override
	public void removeComment(int id) {
		commentDao.removeComment(id);
		
	}

}
