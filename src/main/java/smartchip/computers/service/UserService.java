package smartchip.computers.service;

import java.util.Set;

import smartchip.computers.entities.Role;
import smartchip.computers.entities.User;

public interface UserService {
	//void save(User user);
    User findByUsername(String username);
    User createUser(User user) throws Exception;
    
    User addUser(User user, Set<Role>Role) throws Exception;
}