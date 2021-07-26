package smartchip.computers.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import smartchip.computers.entities.CartItem;
import smartchip.computers.entities.ShoppingCart;


@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long>{
	List<CartItem> findByShoppingCart(ShoppingCart shoppingCart);
}
