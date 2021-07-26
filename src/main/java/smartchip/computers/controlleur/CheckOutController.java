package smartchip.computers.controlleur;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import smartchip.computers.entities.CartItem;
import smartchip.computers.entities.ShoppingCart;
import smartchip.computers.entities.User;
import smartchip.computers.repository.UserRepository;
import smartchip.computers.service.SecurityService;
import smartchip.computers.services.CartItemService;
import smartchip.computers.services.ShoppingCartService;

@Controller
public class CheckOutController {
	
	@Autowired
    private CartItemService cartItemService;
	
	@Autowired
    private UserRepository userRepository;
	
	@Autowired
    private SecurityService securityService;
	
	@Autowired
    private ShoppingCartService shoppingCartService;
	
	@RequestMapping("/payment")
	public String checkOut(Model model, Principal principal) {
		if (securityService.isAuthenticated()) {
			 User user = userRepository.findByUsername(principal.getName());
				
				ShoppingCart shoppingCart = user.getShoppingCart();
				List<CartItem> cartItemList = cartItemService.findByShoppingCart(shoppingCart);
				
				shoppingCartService.updateShoppingCart(shoppingCart);
				
				model.addAttribute("cartItemList", cartItemList);
				model.addAttribute("total", shoppingCart.getGrandTotal());
				model.addAttribute("shoppingCart", shoppingCart);
				return "payment";
		}
		return "redirect:/login";
	}
}
