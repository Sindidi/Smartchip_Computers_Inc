package smartchip.computers.controlleur;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLConnection;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import smartchip.computers.entities.Produit;
import smartchip.computers.repository.ProduitRepository;

@Controller
public class ProduitController {
	
	@Autowired
	private ProduitRepository produitRepository;
	
	//////////////////// Page Product with PAGINATION ///////////////////////////////////
	@RequestMapping(value = "/produit", method = RequestMethod.GET)
	public String showproduit(Model model,
						  @RequestParam(name="page", defaultValue ="0") int page,
						  @RequestParam(name="size", defaultValue ="5") int size,
						  @RequestParam(name="motCle", defaultValue ="") String mc
										)throws IOException 
	{
	
	Page<Produit> produit = produitRepository.search("%"+mc+"%", PageRequest.of(page, size));
	int[] pages = new int[produit.getTotalPages()];
	model.addAttribute("pages", pages);
	model.addAttribute("actuPage", page);
	model.addAttribute("size", size);
	model.addAttribute("motCle", mc);
	model.addAttribute("listProduit", produit);
	return "produit";	
	}
	
	//////////////////// Show Page Add Product And Save a Product ///////////////////////////////////
	@GetMapping("/produitAdd")
    public String showproduitAdd(Produit produit) {
        return "produitAdd";
    }
	
	@PostMapping("/saveproduit")
    public String saveproduit(@RequestParam("myfile") MultipartFile myfile,
    						  @Valid Produit produit, BindingResult result, Model model)throws IOException {
        
        produit.setName(myfile.getOriginalFilename());
        produit.setImage(myfile.getBytes());
        if (result.hasErrors()) {
            return "produitAdd";
        }
        produitRepository.save(produit);
        return "redirect:/produit";
    }

	//////////////////// Show Page Edit Product And Update a Product ///////////////////////////////////
	@RequestMapping(value="produitUpdat/{id}", method = RequestMethod.GET)
	public String produitUpdate(@PathVariable("id") long id,String name,  Model model) {
		Produit produit = produitRepository.findById(id)	
	      .orElseThrow(() -> new IllegalArgumentException("Invalid Produit Id:" + id));
	    model.addAttribute("produit", produit);
	    return "produitUpdate";
	}
	
	@RequestMapping(value="/produitUpddate/{id}", method = RequestMethod.POST)
	public String produitUpddate(@RequestParam("myfile") MultipartFile myfile,
								 @PathVariable("id") long id, @Valid Produit produit, 
								 BindingResult result, Model model)throws IOException {
		produit.setName(myfile.getOriginalFilename());
        produit.setImage(myfile.getBytes());
	    if (result.hasErrors()) {
	    	produit.getName();
	    	produit.setId(id);
	        return "produitUpddate";
	    }
	        
	    produitRepository.save(produit);
	    return "redirect:/produit";
	}
	
	/////////////// display Image By findByName   ////////////////////////////// 
	@GetMapping("/images/{nameImage}")
	public void getImage(@PathVariable("nameImage") String nameImage, HttpServletResponse response) throws Exception {

		Optional<Produit> img = produitRepository.findByName(nameImage);
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

	/////////////// delete Product By findBById   ////////////////////////////// 	
	@GetMapping("/delete/{id}")
	public String deleteProduit(@PathVariable("id") long id,String motCle, int page,int size, Model model) {
		Produit produit = produitRepository.findById(id)
	      .orElseThrow(() -> new IllegalArgumentException("Invalid produit Id:" + id));
		produitRepository.delete(produit);
	    return "redirect:/produit?page="+page+"&size="+size+"&motCle="+motCle;
	}
	
	/////////////// display Details Product   ////////////////////////////// 
	@GetMapping("/image/produitdetails")
	String productDetail(@RequestParam("id") Long id, Optional<Produit> produit, Model model) {
		try {
			
			if (id != 0) {
				produit = produitRepository.findById(id);
				if (produit.isPresent()) {
					model.addAttribute("design", produit.get().getDesign());
					model.addAttribute("desc", produit.get().getDesc());
					model.addAttribute("name", produit.get().getName());
					model.addAttribute("prix", produit.get().getPrix());
					return "produitdetails";
				}
				return "redirect:/produit";
			}
		return "redirect:/produit";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/produit";
		}	
	}
	
	
}
