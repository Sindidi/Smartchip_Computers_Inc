package smartchip.computers.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import smartchip.computers.entities.Produit;
import smartchip.computers.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<Produit> findByName(String nameImage);
    User findByUsername(String username);
}
