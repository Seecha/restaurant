package cz.uhk.restaurace.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cz.uhk.restaurace.dao.DishLocDao;
import cz.uhk.restaurace.model.DishLoc;
import cz.uhk.restaurace.service.DishLocService;
@Service
public class DishLocServiceImpl implements DishLocService {
	@Autowired
	private DishLocDao dishLocDao;

	@Transactional
	@Override
	public void addDishLoc(DishLoc dish) {
		dishLocDao.addDishLoc(dish);

	}

	@Transactional
	@Override
	public void updateDishLoc(DishLoc dish) {
		dishLocDao.updateDishLoc(dish);;

	}

	@Transactional(readOnly = true)
	@Override
	public DishLoc getDishLocById(Integer id, String language) {
		return dishLocDao.getDishLocById(id, language);
	}

	@Transactional
	@Override
	public void removeDishLoc(Integer id, String language) {
		dishLocDao.removeDishLoc(id, language);

	}

	@Transactional
	@Override
	public void removeDishesLoc(int id) {
		dishLocDao.removeDishesLoc(id);
		
	}

}
