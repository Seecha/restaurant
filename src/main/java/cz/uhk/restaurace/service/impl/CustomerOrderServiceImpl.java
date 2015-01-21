package cz.uhk.restaurace.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cz.uhk.restaurace.model.DishGeneral;
import cz.uhk.restaurace.model.DishIngredient;
import cz.uhk.restaurace.model.IngredientGeneral;
import cz.uhk.restaurace.model.OrderDish;
import cz.uhk.restaurace.service.CustomerOrderService;
import cz.uhk.restaurace.service.DishGeneralService;
import cz.uhk.restaurace.service.IngredientGeneralService;
import cz.uhk.restaurace.service.TempCustomerInfoService;
import cz.uhk.restaurace.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cz.uhk.restaurace.dao.CustomerOrderDao;
import cz.uhk.restaurace.model.CustomerOrder;

@Service
public class CustomerOrderServiceImpl implements CustomerOrderService {

	@Autowired
	private CustomerOrderDao customerOrderDao;

	@Autowired
	private UserService userService;

	@Autowired
	private TempCustomerInfoService tempCustomerInfoService;

	@Autowired
	private DishGeneralService dishGeneralService;

	@Autowired
	private IngredientGeneralService ingredientGeneralService;

	@Override
	@Transactional
	public void addOrder(CustomerOrder customerOrder) {
		customerOrderDao.addOrder(customerOrder);

	}

	@Override
	@Transactional
	public void updateOrder(CustomerOrder customerOrder) {
		customerOrderDao.updateOrder(customerOrder);

	}

	@Override
	@Transactional(readOnly = true)
	public List<CustomerOrder> getUserOrdersByUsername(String username) {
		return customerOrderDao.listUserOrder(username);
	}

	@Override
	@Transactional(readOnly = true)
	public CustomerOrder getOrderById(int id, String language) {
		CustomerOrder order = customerOrderDao.getOrderById(id);
		fillOrdersTransientFields(order, language);
		return order;
	}

	@Override
	@Transactional
	public void removeOrder(int id) {
		customerOrderDao.removeOrder(id);

	}

	@Override
	public CustomerOrder createCart() {
		CustomerOrder cart = new CustomerOrder();
		Map<Integer, DishGeneral> orderedDishes = new HashMap<Integer, DishGeneral>();
		cart.setOrderedDishes(orderedDishes);
		return cart;
	}

	@Override
	@Transactional(readOnly = true)
	public List<CustomerOrder> getUnprocessedRegisteredCustomerOrders(
			String language) {
		List<CustomerOrder> orders = customerOrderDao
				.getUnprocessedRegisteredCustomerOrders();
		// Fill transient fields
		for (CustomerOrder order : orders) {
			fillOrdersTransientFields(order, language);
		}
		return orders;
	}

	@Override
	@Transactional(readOnly = true)
	public List<CustomerOrder> getUnprocessedNotregisteredCustomerOrders(
			String language) {
		List<CustomerOrder> orders = customerOrderDao
				.getUnprocessedNotregisteredCustomerOrders();
		// Fill transient fields

		for (CustomerOrder order : orders) {
			fillOrdersTransientFields(order, language);
		}
		return orders;
	}

	@Override
	@Transactional
	public void processOrder(int id) {
		CustomerOrder order = customerOrderDao.getOrderById(id);
		order.setProcessed(true);
		customerOrderDao.updateOrder(order);

	}

	@Override
	@Transactional
	public List<CustomerOrder> getProcessedRegisteredCustomerOrders(
			String language) {
		return customerOrderDao.getProcessesRegisteredCustomerOrder();
	}

	@Override
	@Transactional
	public List<CustomerOrder> getProcessedNotregisteredCustomerOrders(
			String language) {
		return customerOrderDao.getProcessesNotregisteredCustomerOrder();
	}
	
	private CustomerOrder fillOrdersTransientFields(CustomerOrder order, String language){
		Map<String, DishGeneral> teppanyakis = new HashMap<String, DishGeneral>();
		Map<Integer, DishGeneral> dishes = new HashMap<Integer, DishGeneral>();
		for (OrderDish orderDish : order.getOrderedDishesToPersist()) {
			DishGeneral dish = orderDish.getDish();
			if (dish.getDishIngredients().isEmpty()) {
				// Its a dish
				if (dish.getAmountsInOrders().get(order.getId()) == null) {
					dish.getAmountsInOrders().put(order.getId(),
							orderDish.getAmount());
				}
				dishes.put(dish.getId(), dish);
			} else {
				// Its a teppanyaki
				dish.setAmount(orderDish.getAmount());
				for (DishIngredient dishIngredient : dish
						.getDishIngredients()) {
					if (dish.getIngredients().get(
							dishIngredient.getIngredient().getId()) == null) {
						IngredientGeneral ingredient = dishIngredient
								.getIngredient();
						dish.getIngredients().put(
								dishIngredient.getIngredient().getId(),
								ingredient);
						if (ingredient.getGramsInDishes().get(dish.getId()) == null) {
							ingredient.getGramsInDishes().put(dish.getId(),
									dishIngredient.getGrams());
						}
					}
				}
				ingredientGeneralService.getIngredientsLocalized(
						dish.getIngredients(), language);
				teppanyakis.put(dish.getName(), dish);
			}

		}
		order.setOrderedDishes(dishGeneralService.getLocalizedDishesInCart(
				dishes, language));
		order.setOrderedTeppanyakiDishes(dishGeneralService
				.getLocalizedTeppanyakiDishes(teppanyakis, language));
		return order;
		
	}
}
