package services.impl;


import enteties.Order;
import services.OrderManagementService;

public class DefaultOrderManagementService implements OrderManagementService {

	private static final int DEFAULT_ORDER_CAPACITY = 10;

	private static DefaultOrderManagementService instance;

	// <write your code here>
	
	public static OrderManagementService getInstance() {
		if (instance == null) {
			instance = new DefaultOrderManagementService();
		}
		return instance;
	}

	@Override
	public void addOrder(Order order) {
		// <write your code here>
	}

	@Override
	public Order[] getOrdersByUserId(int userId) {
		// <write your code here>
		return null;
	}

	@Override
	public Order[] getOrders() {
		// <write your code here>
		return null;
	}
	
	void clearServiceState() {
		// <write your code here>
	}

}
