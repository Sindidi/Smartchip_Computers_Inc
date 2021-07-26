package smartchip.computers.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import smartchip.computers.entities.Produit;
import smartchip.computers.repository.ProduitRepository;


@Service
public class ProduitServiceImpl implements ProduitService{
	@Autowired
	private ProduitRepository produitRepository;
	
	public List<Produit> findAll() {
		List<Produit> produitList = (List<Produit>) produitRepository.findAll();
		List<Produit> activeProduitList = new ArrayList<>();
		
		for (Produit produit: produitList) {
			if(produit.isActive()) {
				activeProduitList.add(produit);
			}
		}
		
		return activeProduitList;
	}
	
	public Produit findById(Long id) {
		return produitRepository.findById(id).orElse(null);
	}
	
	public List<Produit> findByCategory(String category){
		List<Produit> produitList = produitRepository.findByCategory(category);
		
		List<Produit> activeProduitList = new ArrayList<>();
		
		for (Produit produit: produitList) {
			if(produit.isActive()) {
				activeProduitList.add(produit);
			}
		}
		
		return activeProduitList;
	}

	@Override
	public List<Produit> search(String design) {
		List<Produit> produitList = produitRepository.findByDesignation(design);
        List<Produit> activeProduitList = new ArrayList<>();
		
		for (Produit produit: produitList) {
			if(produit.isActive()) {
				activeProduitList.add(produit);
			}
		}
		
		return activeProduitList;
	}
	
	

}
