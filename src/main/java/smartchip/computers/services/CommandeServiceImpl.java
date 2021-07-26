package smartchip.computers.services;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smartchip.computers.entities.CartItem;
import smartchip.computers.entities.Commande;
import smartchip.computers.entities.Payment;
import smartchip.computers.entities.Produit;
import smartchip.computers.entities.ShoppingCart;
import smartchip.computers.entities.User;
import smartchip.computers.repository.CommandeRepository;


@Service
public class CommandeServiceImpl implements CommandeService{
	
	@Autowired
	private CommandeRepository commandeRepository;
	
	@Autowired
	private CartItemService cartItemService;
	
	public synchronized Commande createOrder(ShoppingCart shoppingCart,
			//ShippingAddress shippingAddress,
			//BillingAddress billingAddress,
			Payment payment,
			String shippingMethod,
			User user) {
		Commande commande = new Commande();
		//commande.setBillingAddress(billingAddress);
		commande.setOrderStatus("created");
		commande.setPayment(payment);
		//commande.setShippingAddress(shippingAddress);
		commande.setShippingMethod(shippingMethod);
		
		List<CartItem> cartItemList = cartItemService.findByShoppingCart(shoppingCart);
		
		for(CartItem cartItem : cartItemList) {
			Produit produit = cartItem.getProduit();
			cartItem.setCommande(commande);
			produit.setQty(produit.getQty() - cartItem.getQty());
		}
		
		commande.setCartItemList(cartItemList);
		commande.setOrderDate(Calendar.getInstance().getTime());
		commande.setOrderTotal(shoppingCart.getGrandTotal());
		//shippingAddress.setCommande(commande);
		//billingAddress.setCommande(commande);
		payment.setCommande(commande);
		commande.setUser(user);
		commande = commandeRepository.save(commande);
		
		return commande;
	}
	
	public Commande findById(Long id) {
		return commandeRepository.findById(id).orElse(null);
	}

}
