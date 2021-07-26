package smartchip.computers.services;

import java.util.List;

import smartchip.computers.entities.CartItem;
import smartchip.computers.entities.Produit;
import smartchip.computers.entities.ShoppingCart;
import smartchip.computers.entities.User;


public interface CartItemService {
	
	List<CartItem> findByShoppingCart(ShoppingCart shoppingCart);
	
	CartItem updateCartItem(CartItem cartItem);
	
	CartItem addProduitToCartItem(Produit produit, User user, int qty);
	
	//CartItem updateQtyCartItem( BigDecimal subtotal,int qty);
	
	CartItem findById(Long id);
	
	void removeCartItem(CartItem cartItem);
	
	CartItem save(CartItem cartItem);

	
}
