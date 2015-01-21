package cz.uhk.restaurace.dao;

import java.util.List;

import cz.uhk.restaurace.model.IngredientLoc;

public interface IngredientLocDao {
	public void addIngredientLoc(IngredientLoc ingredientLoc);
	public void updateIngredientLoc(IngredientLoc ingredientLoc);
	public List<IngredientLoc> listIngredientLoc(String lang);
	public IngredientLoc getIngredientLocById(int id, String language);
	public void removeIngredientLoc(int id, String language);
	public void removeIngredientsLoc(int id);

}
