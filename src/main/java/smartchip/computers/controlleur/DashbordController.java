package smartchip.computers.controlleur;


import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import smartchip.computers.entities.Produit;
import smartchip.computers.entities.User;
import smartchip.computers.repository.ProduitRepository;
import smartchip.computers.repository.UserRepository;
import smartchip.computers.service.SecurityService;

@Controller
public class DashbordController {
	
	
	@Autowired
	private ProduitRepository produitRepository;
	
	@Autowired
    private SecurityService securityService;
	
	@Autowired
	private UserRepository userRepository;
	
	////////////// Page Index DashBord with PAGINATION //////////////////
	@RequestMapping(value = {"/admin", "/dashbord"}, method = RequestMethod.GET)
	public String dashbord(Model model,
										@RequestParam(name="page", defaultValue ="0") int page,
										@RequestParam(name="size", defaultValue ="5") int size,
										@RequestParam(name="motCle", defaultValue ="") String mc
														)throws IOException 
	{
		Page<Produit> produit = produitRepository.search("%"+mc+"%", PageRequest.of(page, size));
		List<User> user = userRepository.findAll();
		model.addAttribute("listUser", user);
		int[] pages = new int[produit.getTotalPages()];
		model.addAttribute("pages", pages);
		model.addAttribute("actuPage", page);
		model.addAttribute("size", size);
		model.addAttribute("motCle", mc);
		model.addAttribute("listProduit", produit);
		if (securityService.isAuthenticated()) {
	        return "dashbord";
	    }
		return "redirect:/login";
			
	}
	
}
