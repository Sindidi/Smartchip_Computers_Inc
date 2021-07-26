package smartchip.computers.controlleur;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLConnection;
import java.security.Principal;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import smartchip.computers.entities.Produit;
import smartchip.computers.entities.User;
import smartchip.computers.repository.UserRepository;
import smartchip.computers.service.SecurityService;

@Controller
public class ProfileController {
	
	@Autowired
    private SecurityService securityService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
    private CartController cartController;  
	
	/////////////////////////// Page Profile ///////////////////////////
	@GetMapping("/profile")
	public String profile(Model model, Principal principal, User user ) {
		if(securityService.isAuthenticated()) {
			user = userRepository.findByUsername(principal.getName());
			model.addAttribute("userInfo", user);
			cartController.shoppingCart(model, principal);
			return "profile";
		}
		return "login";
	}
    
	/////////////////////////// Show Page Edit Profile ///////////////////////////
	@GetMapping("/editProfile")
	public String editProfile(Model model, Principal principal, User user ) {
		if(securityService.isAuthenticated()) {
			user = userRepository.findByUsername(principal.getName());
			model.addAttribute("userInfo", user);
			cartController.shoppingCart(model, principal);
			return "editProfile";
		}
		return "login";
	} 
	
	/////////////////////////// Edit Profile ///////////////////////////
	@RequestMapping(value="/userUpddate/{id}", method = RequestMethod.POST)
	public String produitUpddate(@RequestParam("myfile") MultipartFile myfile,
								 @PathVariable("id") long id, @Valid User user, 
								Model model)throws IOException {
		user.setName(myfile.getOriginalFilename());
		user.setImage(myfile.getBytes());
	    userRepository.save(user);
	    return "redirect:/profile";
	}
	/////////////// display Image By findByName   ////////////////////////////// 
	@GetMapping("/imagesUser/{ImageUser}")
	public void getImageUser(@PathVariable("nameImage") String nameImage, HttpServletResponse response) throws Exception {

		Optional<Produit> img = userRepository.findByName(nameImage);
		if(img.isPresent()) {

			//System.out.println("returning file:"+nameImage);
			byte[] bytes = img.get().getImage();
			InputStream is = new BufferedInputStream(new ByteArrayInputStream(bytes));
			String mimeType = URLConnection.guessContentTypeFromStream(is);
			response.setContentType(mimeType);

			OutputStream outputStream = response.getOutputStream();
			outputStream.write(bytes);
			outputStream.flush();
			outputStream.close();
		}
	}
	
	
	
	/////////////////////////// Page Profile ///////////////////////////
	@GetMapping("/commandeProfile")
	public String commande(Model model, Principal principal, User user ) {
		if(securityService.isAuthenticated()) {
			user = userRepository.findByUsername(principal.getName());
			model.addAttribute("userInfo", user);
			cartController.shoppingCart(model, principal);
			return "commandeProfile";
		}
		return "login";
	}	
}
