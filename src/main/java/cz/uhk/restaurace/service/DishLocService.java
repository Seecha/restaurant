package cz.uhk.restaurace.service;


import cz.uhk.restaurace.model.DishLoc;

public interface DishLocService {
	public void addDishLoc(DishLoc dish);
	public void updateDishLoc(DishLoc dish);
	public DishLoc getDishLocById(Integer id, String language);
	public void removeDishLoc(Integer id, String language);
	public void removeDishesLoc(int id);
}
