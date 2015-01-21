package cz.uhk.restaurace.service;

import java.util.List;

import cz.uhk.restaurace.model.CustomerOrder;

public interface CustomerOrderService {
	public void addOrder(CustomerOrder customerOrder);
	public void updateOrder(CustomerOrder customerOrder);

	/**
	 * Get all customers orders
	 * @param username
	 * @return
	 */
	public List<CustomerOrder> getUserOrdersByUsername(String username);
	
	/**
	 * Gets customer order according to given id and fill its transient fields
	 * @param id
	 * @return
	 */
	public CustomerOrder getOrderById(int id, String language);
	/**
	 * Removes customer order according to given id
	 * @param id
	 */
	public void removeOrder(int id);

	/**
	 * Just create empty cart
	 * @return
	 */
	public CustomerOrder createCart();

	/**
	 * Get new customer orders of registered users and fill their transient fields
	 * @param language
	 * @return
	 */
	public List<CustomerOrder> getUnprocessedRegisteredCustomerOrders(String language);

	/**
	 * Get new customer orders of unregistered users and fill their transient fields
	 * @param language
	 * @return
	 */
	
	public List<CustomerOrder> getUnprocessedNotregisteredCustomerOrders(String language);
	
	/**
	 * Get processed customer orders of registered users and fill their transient fields
	 * @param language
	 * @return
	 */
	public List<CustomerOrder> getProcessedRegisteredCustomerOrders(String language);

	/**
	 * Get processed customer orders of unregistered users and fill their transient fields
	 * @param language
	 * @return
	 */
	
	public List<CustomerOrder> getProcessedNotregisteredCustomerOrders(String language);
	/**
	 * Mark order as processed and save it to DB
	 * @param id
	 */
	public void processOrder(int id);
	
	

}
