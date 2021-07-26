package smartchip.computers.repository;

import org.springframework.data.repository.CrudRepository;
import smartchip.computers.entities.Commande;

public interface CommandeRepository extends CrudRepository<Commande, Long>{

}
