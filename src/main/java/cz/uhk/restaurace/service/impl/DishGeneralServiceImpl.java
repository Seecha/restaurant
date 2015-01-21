package cz.uhk.restaurace.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cz.uhk.restaurace.dao.DishGeneralDao;
import cz.uhk.restaurace.dao.DishLocDao;
import cz.uhk.restaurace.dao.IngredientLocDao;
import cz.uhk.restaurace.model.DishGeneral;
import cz.uhk.restaurace.model.DishGeneral.DishCategory;
import cz.uhk.restaurace.model.IngredientGeneral;
import cz.uhk.restaurace.service.DishGeneralService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class DishGeneralServiceImpl implements DishGeneralService {

	@Autowired
	private DishGeneralDao dishDishGeneralDao;

	@Autowired
	private DishLocDao dishLocDao;

	@Autowired
	private IngredientLocDao ingredientLocDao;

	@Override
	@Transactional
	public void addDish(DishGeneral dish) {
		dishDishGeneralDao.addDishGeneral(dish);
		
	}

	@Override
	@Transactional
	public void updateDish(DishGeneral dish) {
		dishDishGeneralDao.updateDishGeneral(dish);
		
	}

	@Override
	@Transactional(readOnly = true)
	public List<DishGeneral> listDishes(String language) {
		List<DishGeneral> dishes =  dishDishGeneralDao.listDishesGeneral();
		for(DishGeneral dish : dishes){
			dish.setDishLoc(dishLocDao.getDishLocById(dish.getId(),language));
		}
		return dishes;

	}

	@Override
	@Transactional(readOnly = true)
	public List<DishGeneral> listDrinks(String language) {
		List<DishGeneral> drinks =  dishDishGeneralDao.listDrinksGeneral();
		for(DishGeneral drink : drinks){
			drink.setDishLoc(dishLocDao.getDishLocById(drink.getId(),language));
		}
		return drinks;
	}

	@Override
	@Transactional(readOnly = true)
	public DishGeneral getDishById(Integer id) {
		return dishDishGeneralDao.getDishGeneralById(id);
	}

	@Override
	@Transactional
	public void removeDish(Integer id) {
		dishDishGeneralDao.removeDishGeneral(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Map<Integer, DishGeneral> getLocalizedDishesInCart(Map<Integer, DishGeneral> dishes, String language) {
		for(Map.Entry<Integer, DishGeneral> dish : dishes.entrySet()){
			dish.getValue().setDishLoc(dishLocDao.getDishLocById(dish.getValue().getId(), language));
		}
		return dishes;
	}

	@Override
	@Transactional(readOnly = true)
	public Map<String, DishGeneral> getLocalizedTeppanyakiDishes(Map<String, DishGeneral> dishes, String language) {
		for(Map.Entry<String, DishGeneral> dish : dishes.entrySet()){
			for(Map.Entry<Integer, IngredientGeneral> ingr : dish.getValue().getIngredients().entrySet()){
				ingr.getValue().setIngredientLocalized(
						ingredientLocDao.getIngredientLocById(ingr.getValue().getId(), language));
			}
		}
		return dishes;
	}

	@Override
	public DishGeneral createDish() {
		DishGeneral dish = new DishGeneral();
		Map<Integer, IngredientGeneral> ingredients = new HashMap<Integer, IngredientGeneral>();
		dish.setIngredients(ingredients);
		dish.setDishCategory(DishGeneral.DishCategory.TEPPANYAKI);
		return dish;
	}

	@Override
	@Transactional(readOnly = true)
	public Map<String, DishGeneral> getLocalizedCustomDishesInCart(Map<String, DishGeneral> dishes, String language) {
		for (Map.Entry<String, DishGeneral> entry : dishes.entrySet()){
			for (Map.Entry<Integer, IngredientGeneral> ent : entry.getValue().getIngredients().entrySet()){
				ent.getValue().setIngredientLocalized(ingredientLocDao.getIngredientLocById(ent.getValue().getId(), language));
			}
		}
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public Map<Integer, DishGeneral> loadOrderedDishes(Map<Integer, DishGeneral> dishes) {
		return dishDishGeneralDao.loadOrderedDishes(dishes);
	}

	@Override
	public List<DishCategory> getDishCategories() {
		return Arrays.asList(DishGeneral.DishCategory.values());
	}
}
