package cz.uhk.restaurace.dao;

import java.util.List;

import cz.uhk.restaurace.model.CustomerOrder;

public interface CustomerOrderDao {
	public void addOrder(CustomerOrder customerOrder);
	public void updateOrder(CustomerOrder customerOrder);
	public List<CustomerOrder> listUserOrder(String username);
	public CustomerOrder getOrderById(int id);
	public void removeOrder(int id);
	public List<CustomerOrder> getUnprocessedRegisteredCustomerOrders();
	public List<CustomerOrder> getUnprocessedNotregisteredCustomerOrders();
	public List<CustomerOrder> getProcessesRegisteredCustomerOrder();
	public List<CustomerOrder> getProcessesNotregisteredCustomerOrder();
}
