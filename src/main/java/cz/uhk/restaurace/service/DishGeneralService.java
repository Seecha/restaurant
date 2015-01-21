package cz.uhk.restaurace.service;

import java.util.List;
import java.util.Map;

import cz.uhk.restaurace.model.DishGeneral;
import cz.uhk.restaurace.model.DishGeneral.DishCategory;


public interface DishGeneralService {

	public void addDish(DishGeneral dish);

	public void updateDish(DishGeneral dish);

	/**
	 * Get all dishes
	 * @param language
	 * @return
	 */
	public List<DishGeneral> listDishes(String language);

	/**
	 * Get all drinks
	 * @param language
	 * @return
	 */
	public List<DishGeneral> listDrinks(String language);
	public DishGeneral getDishById(Integer id);
	public void removeDish(Integer id);

	/**
	 * Get dishes in cart with localized attributes - in the current language
	 * @param dishes
	 * @param language
	 * @return
	 */
	public Map<Integer, DishGeneral> getLocalizedDishesInCart(Map<Integer, DishGeneral> dishes, String language);

	/**
	 * Get custom dishes in cart with localized attributes - in the current language
	 * @param dishes
	 * @param language
	 * @return
	 */
	public Map<String, DishGeneral> getLocalizedCustomDishesInCart(Map<String, DishGeneral> dishes, String language);

	/**
	 * Get teppanyaki dishes in cart with localized attributes = in the current language
	 * @param dishes
	 * @param language
	 * @return
	 */
	public Map<String, DishGeneral> getLocalizedTeppanyakiDishes(Map<String, DishGeneral> dishes, String language);

	/**
	 * Create empty dish, suitable for storage in a session
	 * @return
	 */
	public DishGeneral createDish();

	/**
	 * Load proxies of given dishes
	 * @param dishes
	 * @return
	 */
	public Map<Integer, DishGeneral> loadOrderedDishes(Map<Integer, DishGeneral> dishes);
	
	/**
	 * Get all dish cathegories
	 * @return
	 */
	public List<DishCategory> getDishCategories();
}
