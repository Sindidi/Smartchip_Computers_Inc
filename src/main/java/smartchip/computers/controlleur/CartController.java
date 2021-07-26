package smartchip.computers.controlleur;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import smartchip.computers.entities.CartItem;
import smartchip.computers.entities.Produit;
import smartchip.computers.entities.ShoppingCart;
import smartchip.computers.entities.User;
import smartchip.computers.repository.UserRepository;
import smartchip.computers.service.SecurityService;
import smartchip.computers.services.CartItemService;
import smartchip.computers.services.ProduitService;
import smartchip.computers.services.ShoppingCartService;

@Controller
@RequestMapping("/shoppingCart")
public class CartController {
	
	@Autowired
	private ProduitService produitService;
	
	@Autowired
    private CartItemService cartItemService;
	
	@Autowired
    private UserRepository userRepository;
	
	@Autowired
    private SecurityService securityService;
	
	@Autowired
    private ShoppingCartService shoppingCartService;
	

	@RequestMapping("/cart")
	public String shoppingCart(Model model, Principal principal) {
		 if (securityService.isAuthenticated()) {
			 User user = userRepository.findByUsername(principal.getName());
				
				ShoppingCart shoppingCart = user.getShoppingCart();
				List<CartItem> cartItemList = cartItemService.findByShoppingCart(shoppingCart);
				
				shoppingCartService.updateShoppingCart(shoppingCart);
				
				model.addAttribute("cartItemList", cartItemList);
				model.addAttribute("somme", shoppingCart.getGrandTotal());
				model.addAttribute("shoppingCart", shoppingCart);
				
				List<Integer> qtyList = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
				model.addAttribute("qtyList", qtyList);
				//model.addAttribute("qty", 1);
				return "cartItem";  
	        }
		 return "redirect:/login";
		
	}
		
	@SuppressWarnings("unused")
	@RequestMapping("/addItemCart")
	public String addItem(@ModelAttribute("produit") Produit produit,
						  @ModelAttribute("qty") String qty,
						  Model model, Principal principal){
		User user = userRepository.findByUsername(principal.getName());
		produit = produitService.findById(produit.getId());
		
		if (Integer.parseInt(qty) > produit.getQty()) {
			model.addAttribute("notStock", true);
			return "redirect:/detailProduct?id="+produit.getId();
		}
		
		CartItem cartItem = cartItemService.addProduitToCartItem(produit, user, Integer.parseInt(qty));
		model.addAttribute("msg", "addSuccess");
		model.addAttribute("addSuccess", true);
		
		return "redirect:/registration?success";
	}
	
	
	@RequestMapping("/updateCartItem")
	public String updateShoppingCart(@ModelAttribute("id") Long cartItemId,
									 @ModelAttribute("qty") int qty) {
		CartItem cartItem = cartItemService.findById(cartItemId);
		cartItem.setQty(qty);
		cartItemService.updateCartItem(cartItem);
		
		return "forward:/shoppingCart/cart";
	}
	
	
	@RequestMapping("/removeItem")
	public String removeItem(@RequestParam("id") Long id) {
		cartItemService.removeCartItem(cartItemService.findById(id));
		return "forward:/shoppingCart/cart";
	}
	
}
