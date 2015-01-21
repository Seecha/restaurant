package cz.uhk.restaurace.service;



import java.util.List;

import cz.uhk.restaurace.model.IngredientLoc;

public interface IngredientLocService {
	public void addIngredientLoc(IngredientLoc ingredientLoc);
	public void updateIngredientLoc(IngredientLoc ingredientLoc);
	public IngredientLoc getIngredientLocById(Integer id, String language);
	public void removeIngredientLoc(Integer id, String language);
	public List<IngredientLoc> listIngredientsLoc(String lang);
	public void removeIngredientsLoc(int id);

}
