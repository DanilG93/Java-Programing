package enteties.impl;


import enteties.Cart;
import enteties.Product;

public class DefaultCart implements Cart {


	
	@Override
	public boolean isEmpty() {
		// <write your code here>
		
		return true;
	}

	@Override
	public void addProduct(Product product) {
		// <write your code here>
	}

	@Override
	public Product[] getProducts() {
		// <write your code here>
		
		return null;
	}

	@Override
	public void clear() {
		// <write your code here>
	}

}
