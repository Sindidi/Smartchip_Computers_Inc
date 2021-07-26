package smartchip.computers.controlleur;


import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import smartchip.computers.entities.Produit;
import smartchip.computers.entities.User;
import smartchip.computers.repository.ProduitRepository;
import smartchip.computers.repository.UserRepository;
import smartchip.computers.services.ProduitService;


@Controller
public class SearchController {
	
	@Autowired
	private ProduitRepository produitRepository;
	
	@Autowired
	private ProduitService produitService;
	//@Autowired
	//private SecurityService securityService;
	@Autowired
	private UserRepository userRepository; 
	
	@Autowired
	private CartController cartController;

	/////// ------------------- ///////////////////////////////////////
	@RequestMapping("/searchByCategory")
	public String searchByCategory(
			@RequestParam("category") String category,
			Model model, Principal principal
			){
		if(principal!=null) {
			String username = principal.getName();
			User user = userRepository.findByUsername(username);
			model.addAttribute("user", user);
		}
		
		String classActiveCategory = "active"+category;
		classActiveCategory = classActiveCategory.replaceAll("\\s+", "");
		classActiveCategory = classActiveCategory.replaceAll("&", "");
		model.addAttribute(classActiveCategory, true);
		
		List<Produit> produitList = produitService.findByCategory(category);
		
		if (produitList.isEmpty()) {
			model.addAttribute("empty", true);
			cartController.shoppingCart(model, principal);
			return "searchProduit";
		}
		
		model.addAttribute("produitList", produitList);
		cartController.shoppingCart(model, principal);
		return "searchProduit";
	}
	@RequestMapping("/searchByAllCategory")
	public String searchByAllCategory(
			@RequestParam("category") String category,
			Model model, Principal principal
			){
		if(principal!=null) {
			String username = principal.getName();
			User user = userRepository.findByUsername(username);
			model.addAttribute("user", user);
		}
		
		String classActiveCategory = "active"+category;
		classActiveCategory = classActiveCategory.replaceAll("\\s+", "");
		classActiveCategory = classActiveCategory.replaceAll("&", "");
		model.addAttribute(classActiveCategory, true);
		
		List<Produit> produitAll = produitService.findAll();
		
		if (produitAll.isEmpty()) {
			model.addAttribute("empty", true);
			cartController.shoppingCart(model, principal);
			return "searchProduit";
		}
		
		model.addAttribute("produitAll", produitAll);
		cartController.shoppingCart(model, principal);
		return "searchProduit";
	}
	
	/////// ------------------- ///////////////////////////////////////
	@RequestMapping("/searchProduit")
	public String searchProuit(@ModelAttribute("keyword") String keyword,
							 Principal principal, Model model) {
		if(principal!=null) {
			String username = principal.getName();
			User user = userRepository.findByUsername(username);
			model.addAttribute("user", user);
		}
		
		List<Produit> produitList = produitRepository .findByDesignation("%"+keyword+"%");
		
		if (produitList.isEmpty()) {
			model.addAttribute("empty", true);
			cartController.shoppingCart(model, principal);
			return "search";
		}
		model.addAttribute("keyword", keyword);
		model.addAttribute("produitList", produitList);
		cartController.shoppingCart(model, principal);
		return "search";
	}
}