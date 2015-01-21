package cz.uhk.restaurace.service;

import java.util.List;
import java.util.Map;

import cz.uhk.restaurace.model.IngredientGeneral;
import cz.uhk.restaurace.model.IngredientLoc;

/**
 * This service encapsulates common operations with ingredients
 */
public interface IngredientGeneralService {

	/**
	 * Add one ingredient to DB
	 * @param ingredient
	 */
	public void addIngredient(IngredientGeneral ingredient);

	/**
	 * Update ingredient in DB
	 * this update methods may not actually be needed
	 * @param ingredient
	 */
	public void updateIngredient(IngredientGeneral ingredient);

	/**
	 * Get list of all ingredients without localized describing fields
	 * @return
	 */
	public List<IngredientGeneral> listIngredient();

	/**
	 * Retrieve single ingredient by provided id
	 * @param id
	 * @return
	 */
	public IngredientGeneral getIngredientById(int id);

	/**
	 * Remove ingredient from DB
	 * @param id
	 */
	public void removeIngredient(int id);

	/**
	 * Get all ingredients by their ingredient type and language actually being used in app
	 * @param type
	 * @param language
	 * @return
	 */
	public List<IngredientGeneral> getIngredientsByCategory(IngredientGeneral.IngredientType type, String language);

	/**
	 * Get ingredients with localized attributes
	 * @param ingredients
	 * @param language
	 * @return
	 */
	public Map<Integer, IngredientGeneral> getIngredientsLocalized(Map<Integer, IngredientGeneral> ingredients, String language);

	/**
	 * Get localized version of ingredient given
	 * @param id
	 * @param language
	 * @return
	 */
	public IngredientLoc getIngredientLocalized(Integer id, String language);

	/**
	 * Get all ingredient types
	 * @return
	 */
	public List<IngredientGeneral.IngredientType> getIngredientTypes();

	/**
	 * Actualize attr values of given ingredients (on language change request typically)
	 * @param ingredients
	 * @param language
	 */
	public void actualizeLocFieldsOnIngredients(Map<Integer, IngredientGeneral> ingredients, String language);

	/**
	 * Get proxies of given ingredients for join tables purposes
	 * @param ingredients
	 * @return
	 */
	public Map<Integer, IngredientGeneral> loadIngredientsGeneral(Map<Integer, IngredientGeneral> ingredients);
}
