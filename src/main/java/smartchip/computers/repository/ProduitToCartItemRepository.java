package smartchip.computers.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import smartchip.computers.entities.CartItem;
import smartchip.computers.entities.ProduitToCartItem;

@Transactional
public interface ProduitToCartItemRepository extends CrudRepository<ProduitToCartItem, Long> {
	
	void deleteByCartItem(CartItem cartItem);

}
