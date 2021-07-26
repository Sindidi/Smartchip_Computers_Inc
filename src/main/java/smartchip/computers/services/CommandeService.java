package smartchip.computers.services;

import smartchip.computers.entities.Commande;
import smartchip.computers.entities.Payment;
import smartchip.computers.entities.ShoppingCart;
import smartchip.computers.entities.User;

public interface CommandeService {
	Commande createOrder(ShoppingCart shoppingCart,
			//ShippingAddress shippingAddress,
			//BillingAddress billingAddress,
			Payment payment,
			String shippingMethod,
			User user);
	
	Commande findById(Long id);
}
