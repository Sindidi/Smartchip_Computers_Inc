package smartchip.computers.services;

import smartchip.computers.entities.ShoppingCart;

public interface ShoppingCartService {
	
	ShoppingCart updateShoppingCart(ShoppingCart shoppingCart);
	
	void clearShoppingCart(ShoppingCart shoppingCart);
}
