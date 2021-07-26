package smartchip.computers.services;

import java.util.List;

import smartchip.computers.entities.Produit;


public interface ProduitService {
	List<Produit> findAll ();
	
	Produit findById(Long id);
	
	List<Produit> findByCategory(String category);
	
	List<Produit> search(String design);

}
