package smartchip.computers.repository;



import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;
import smartchip.computers.entities.Produit;

@Repository
public interface ProduitRepository extends CrudRepository<Produit, Long>, QueryByExampleExecutor <Produit> {
	
	Optional<Produit> findByName(String nameImage);
	
	@Query(" SELECT p from Produit p where p.design like:x ")
	public Page<Produit> search(@Param("x") String mc, Pageable pageable);
	
	List<Produit> findByCategory(String category);
	
	@Query(" SELECT p from Produit p where concat (p.category,p.design,p.marque) like:x ")
	List<Produit> findByDesignation(@Param("x") String design);

	
}
