package smartchip.computers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import smartchip.computers.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	
	Role findByname(String name);
	
}
