package smartchip.computers.repository;

import org.springframework.data.repository.CrudRepository;

import smartchip.computers.entities.ShoppingCart;

public interface ShoppingCartRepository extends CrudRepository<ShoppingCart, Long> {

}
