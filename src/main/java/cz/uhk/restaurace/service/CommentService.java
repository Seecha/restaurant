package cz.uhk.restaurace.service;

import java.util.List;

import cz.uhk.restaurace.model.Comment;
import cz.uhk.restaurace.model.DishGeneral;

public interface CommentService {
	public void addComment(Comment comment);
	public List<Comment> getCommentsByDish(DishGeneral dish);
	public void removeComment(int id);

}
