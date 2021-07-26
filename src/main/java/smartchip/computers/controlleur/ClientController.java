package smartchip.computers.controlleur;



import java.io.IOException;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;

import javax.websocket.server.PathParam;

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
import smartchip.computers.service.UserService;
import smartchip.computers.services.ProduitService;

@Controller
public class ClientController {
	
	@Autowired
	private ProduitRepository produitRepository; 
	
	@Autowired
	private ProduitService produitService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
    private CartController cartController;
	
	String catOrdi = "Ordinateurs";
	String catImp = "Imprimantes";
	String catMem = "Memoires";
	String catCar = "Cartes";
	/////////////// Lister Les Produits  Par Paginiation    ////////////////////////////// 
	@RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
	public String index(Model model, Principal principal, 
						@RequestParam(name="page", defaultValue ="0") int page,
						@RequestParam(name="size", defaultValue ="4") int size,
						@RequestParam(name="motCle", defaultValue ="") String mc)throws IOException 
	{

		Page<Produit> produits = produitRepository.search("%"+mc+"%", PageRequest.of(page, size));
		List<Produit> produitsOrdi = produitRepository .findByDesignation("%"+catOrdi+"%");
		List<Produit> produitscatImp = produitRepository .findByDesignation("%"+catImp+"%");
		List<Produit> produitscatMem = produitRepository .findByDesignation("%"+catMem+"%");
		List<Produit> produitscatCar = produitRepository .findByDesignation("%"+catCar+"%");
		int[] pages = new int[produits.getTotalPages()];
		model.addAttribute("pages", pages);
		model.addAttribute("actuPage", page);
		model.addAttribute("size", size);
		model.addAttribute("motCle", mc);
		model.addAttribute("produits", produits);
		model.addAttribute("produitsOrdi", produitsOrdi);
		model.addAttribute("produitscatImp", produitscatImp);
		model.addAttribute("produitscatMem", produitscatMem);
		model.addAttribute("produitscatCar", produitscatCar);
		cartController.shoppingCart(model, principal);
		return "index";		
	}
	////////////// Lister Ordinateur   //////////////////////////////
	@RequestMapping(value = "/produit_Ordinateur", method = RequestMethod.GET)
	public String Ordinateur(Model model, Principal principal) {
		List<Produit> ordinateur = (List<Produit>) produitRepository.findByDesignation("%"+catOrdi+"%");
		model.addAttribute("ordinateur", ordinateur);
		cartController.shoppingCart(model, principal);
		return "ProuitsOrdi";	
	}
	
	////////////// Lister Imprimante   //////////////////////////////
	@RequestMapping(value = "/produit_Imprimante", method = RequestMethod.GET)
	public String Imprimante(Model model, Principal principal) {
		List<Produit> imprimante = (List<Produit>) produitRepository.findByDesignation("%"+catImp+"%");
		model.addAttribute("imprimante", imprimante);
		cartController.shoppingCart(model, principal);
		return "ProuitsImpri";	
	}

	////////////// Lister Memoire   //////////////////////////////
	@RequestMapping(value = "/produit_Memoire", method = RequestMethod.GET)
	public String Memoire(Model model, Principal principal) {
		List<Produit> memoire = (List<Produit>) produitRepository.findByDesignation("%"+catMem+"%");
		model.addAttribute("memoire", memoire);
		cartController.shoppingCart(model, principal);
		return "ProuitsMem";	
	}

	////////////// Lister Carte   //////////////////////////////
	@RequestMapping(value = "/produit_Carte", method = RequestMethod.GET)
	public String Carte(Model model, Principal principal) {
		List<Produit> carte = (List<Produit>) produitRepository.findByDesignation("%"+catCar+"%");
		model.addAttribute("carte", carte);
		cartController.shoppingCart(model, principal);
		return "ProuitsCart";	
	}	
	
	
	/////////////// display Details Product   ////////////////////////////// 
	@RequestMapping("/detailProduct")
	public String detailProduct(@PathParam("id") Long id, Model model, Principal principal) {
		if(principal != null) {
			String username = principal.getName();
			User user = userService.findByUsername(username);
			model.addAttribute("user", user);
		}
		Produit produits = produitService.findById(id);
		
		model.addAttribute("produits", produits);
		
		List<Integer> qtyList = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
		
		model.addAttribute("qtyList", qtyList);
		model.addAttribute("qty", 1);
		cartController.shoppingCart(model, principal);
		return "detailProduct";
	}
	
}
