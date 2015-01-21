package cz.uhk.restaurace.dao;


import java.util.List;

import cz.uhk.restaurace.model.Comment;
import cz.uhk.restaurace.model.DishGeneral;

public interface CommentDao {
	public void addComment(Comment comment);
	public List<Comment> getCommentsByDish(DishGeneral Dish);
	public void removeComment(int id);

}
