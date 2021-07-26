package smartchip.computers.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import smartchip.computers.entities.CartItem;
import smartchip.computers.entities.Produit;
import smartchip.computers.entities.ProduitToCartItem;
import smartchip.computers.entities.ShoppingCart;
import smartchip.computers.entities.User;
import smartchip.computers.repository.CartItemRepository;
import smartchip.computers.repository.ProduitToCartItemRepository;


@Service
public class CartItemServiceImpl implements CartItemService{
	@Autowired
	private CartItemRepository cartItemRepository;
	
	@Autowired
	private ProduitToCartItemRepository produitToCartItemRepository;
	
	
	@Override
	public List<CartItem> findByShoppingCart(ShoppingCart shoppingCart) {
		return cartItemRepository.findByShoppingCart(shoppingCart);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public CartItem updateCartItem(CartItem cartItem) {
		BigDecimal bigDecimal = new BigDecimal(cartItem.getProduit().getPrix()).multiply(new BigDecimal(cartItem.getQty()));
		
		bigDecimal = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
		cartItem.setSubtotal(bigDecimal);
		
		cartItemRepository.save(cartItem);
		
		return cartItem;
	}

	@Override
	public CartItem addProduitToCartItem(Produit produit, User user, int qty) {
		
		List<CartItem> cartItemList = findByShoppingCart(user.getShoppingCart());
			for (CartItem cartItem : cartItemList) {
				if(produit.getId() == cartItem.getProduit().getId()) {
					cartItem.setQty(cartItem.getQty()+qty);
					cartItem.setSubtotal(new BigDecimal(produit.getPrix()).multiply(new BigDecimal(qty)));
					cartItemRepository.save(cartItem);
					return cartItem;
				}
			}
			
			CartItem cartItem = new CartItem();
			cartItem.setShoppingCart(user.getShoppingCart());
			cartItem.setProduit(produit);
			
			cartItem.setQty(qty);
			cartItem.setSubtotal(new BigDecimal(produit.getPrix()).multiply(new BigDecimal(qty)));
			cartItem = cartItemRepository.save(cartItem);
			
			ProduitToCartItem produitToCartItem = new ProduitToCartItem();
			produitToCartItem.setProduit(produit);
			produitToCartItem.setCartItem(cartItem);
			produitToCartItemRepository.save(produitToCartItem);
			
			return cartItem;
	}

	@Override
	public CartItem findById(Long id) {
		return cartItemRepository.findById(id).orElse(null);
	}

	@Override
	public void removeCartItem(CartItem cartItem) {
		produitToCartItemRepository.deleteByCartItem(cartItem);
		cartItemRepository.delete(cartItem);
	}

	@Override
	public CartItem save(CartItem cartItem) {
		return cartItemRepository.save(cartItem);
	}
	

}
