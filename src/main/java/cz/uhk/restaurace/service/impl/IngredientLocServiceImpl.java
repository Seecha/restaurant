package cz.uhk.restaurace.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cz.uhk.restaurace.dao.IngredientLocDao;
import cz.uhk.restaurace.model.IngredientLoc;
import cz.uhk.restaurace.service.IngredientLocService;
@Service
public class IngredientLocServiceImpl implements IngredientLocService {
	
	@Autowired
	IngredientLocDao ingredientLocDao;

	@Transactional
	@Override
	public void addIngredientLoc(IngredientLoc ingredientLoc) {
		ingredientLocDao.addIngredientLoc(ingredientLoc);

	}

	@Transactional
	@Override
	public void updateIngredientLoc(IngredientLoc ingredientLoc) {
		ingredientLocDao.updateIngredientLoc(ingredientLoc);

	}

	@Transactional(readOnly = true)
	@Override
	public IngredientLoc getIngredientLocById(Integer id, String language) {
		return ingredientLocDao.getIngredientLocById(id, language);
	}

	@Transactional
	@Override
	public void removeIngredientLoc(Integer id, String language) {
		ingredientLocDao.removeIngredientLoc(id, language);

	}

	@Override
	@Transactional(readOnly = true)
	public List<IngredientLoc> listIngredientsLoc(String lang) {
		return ingredientLocDao.listIngredientLoc(lang);
	}

	@Override
	@Transactional
	public void removeIngredientsLoc(int id) {
		ingredientLocDao.removeIngredientsLoc(id);
		
	}

}
