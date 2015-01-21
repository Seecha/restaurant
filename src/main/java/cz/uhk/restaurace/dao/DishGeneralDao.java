package cz.uhk.restaurace.dao;

import java.util.List;
import java.util.Map;

import cz.uhk.restaurace.model.DishGeneral;

public interface DishGeneralDao {
	public void addDishGeneral(DishGeneral dish);
	public void updateDishGeneral(DishGeneral dish);
	public List<DishGeneral> listDishesGeneral();
	public List<DishGeneral> listDrinksGeneral();
	public DishGeneral getDishGeneralById(int id);
	public void removeDishGeneral(int id);
	public Map<Integer, DishGeneral> loadOrderedDishes(Map<Integer, DishGeneral> dishes);
}
