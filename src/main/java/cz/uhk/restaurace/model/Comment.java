package cz.uhk.restaurace.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Comment {
	@Id
	@GeneratedValue
	int id;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "username")
	User user;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dish_id")
	DishGeneral dish;
	String comment;
	
	public Comment() {
	
	}
	
	public Comment(User user, DishGeneral dish, String comment){
		this.comment =comment;
		this.user = user;
		this.dish = dish;
		
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public DishGeneral getDish() {
		return dish;
	}

	public void setDish(DishGeneral dish) {
		this.dish = dish;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getId() {
		return id;
	}
	

}
