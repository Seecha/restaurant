package cz.uhk.restaurace.dao;

import java.util.List;
import java.util.Map;

import cz.uhk.restaurace.model.IngredientGeneral;

public interface IngredientGeneralDao {
	public void addIngredientGeneral(IngredientGeneral ingredient);
	public void updateIngredientGeneral(IngredientGeneral ingredient);
	public List<IngredientGeneral> listIngredientsGeneral();
	public List<IngredientGeneral> getIngredientsGeneralByCategory(IngredientGeneral.IngredientType type);
	public IngredientGeneral getIngredientGeneralById(int id);
	public void removeIngredientGeneral(int id);

	/**
	 * Get proxies of given ingredients for join tables purposes
	 * @param ingredients
	 * @return
	 */
	public Map<Integer, IngredientGeneral> loadIngredientsGeneral(Map<Integer, IngredientGeneral> ingredients);
}
