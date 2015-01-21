package cz.uhk.restaurace.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import cz.uhk.restaurace.dao.IngredientGeneralDao;
import cz.uhk.restaurace.dao.IngredientLocDao;
import cz.uhk.restaurace.model.IngredientGeneral;
import cz.uhk.restaurace.model.IngredientLoc;
import cz.uhk.restaurace.service.IngredientGeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class IngredientGeneralServiceImpl implements IngredientGeneralService {

	@Autowired
	private IngredientGeneralDao ingredientGeneralDao;

	@Autowired
	private IngredientLocDao ingredientLocDao;

	@Override
	@Transactional
	public void addIngredient(IngredientGeneral ingredient) {
		ingredientGeneralDao.addIngredientGeneral(ingredient);
	}

	@Override
	@Transactional
	public void updateIngredient(IngredientGeneral ingredient) {
		ingredientGeneralDao.updateIngredientGeneral(ingredient);
	}

	@Override
	@Transactional(readOnly = true)
	public List<IngredientGeneral> listIngredient() {
		return ingredientGeneralDao.listIngredientsGeneral();
	}

	@Override
	@Transactional(readOnly = true)
	public IngredientGeneral getIngredientById(int id) {
		return ingredientGeneralDao.getIngredientGeneralById(id);
	}

	@Override
	@Transactional
	public void removeIngredient(int id) {
		ingredientGeneralDao.removeIngredientGeneral(id);
	}

	@Transactional(readOnly = true)
	@Override
	public List<IngredientGeneral> getIngredientsByCategory(IngredientGeneral.IngredientType type, String language) {
		List<IngredientGeneral> ingredients = ingredientGeneralDao.getIngredientsGeneralByCategory(type);
		for(IngredientGeneral ingr : ingredients){
			ingr.setIngredientLocalized(ingredientLocDao.getIngredientLocById(ingr.getId(), language));
		}
		return ingredients;
	}

	@Override
	@Transactional(readOnly = true)
	public Map<Integer, IngredientGeneral> getIngredientsLocalized(Map<Integer, IngredientGeneral> ingredients, String language) {
		for(Map.Entry<Integer, IngredientGeneral> ingr : ingredients.entrySet()){
			ingr.getValue().setIngredientLocalized(ingredientLocDao.getIngredientLocById(ingr.getValue().getId(), language));
		}
		return ingredients;
	}

	@Override
	@Transactional(readOnly = true)
	public IngredientLoc getIngredientLocalized(Integer id, String language) {
		return ingredientLocDao.getIngredientLocById(id, language);
	}

	@Override
	public List<IngredientGeneral.IngredientType> getIngredientTypes() {
		return Arrays.asList(IngredientGeneral.IngredientType.values());
	}

	@Override
	@Transactional
	public void actualizeLocFieldsOnIngredients(Map<Integer, IngredientGeneral> ingredients, String language) {
		for(Map.Entry<Integer, IngredientGeneral> ingredient : ingredients.entrySet()){
			IngredientGeneral ingr = ingredient.getValue();
			ingr.setIngredientLocalized(getIngredientLocalized(ingr.getId(), language));
		}
	}

	@Override
	@Transactional
	public Map<Integer, IngredientGeneral> loadIngredientsGeneral(Map<Integer, IngredientGeneral> ingredients) {
		return ingredientGeneralDao.loadIngredientsGeneral(ingredients);
	}
}
