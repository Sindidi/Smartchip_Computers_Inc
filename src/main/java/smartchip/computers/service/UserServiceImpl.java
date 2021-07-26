package smartchip.computers.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import smartchip.computers.entities.Role;
import smartchip.computers.entities.ShoppingCart;
import smartchip.computers.entities.User;
import smartchip.computers.entities.UserShipping;
import smartchip.computers.repository.RoleRepository;
import smartchip.computers.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {
	private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
	@Transactional
    public User createUser(User user) {
    	User localUser = userRepository.findByUsername(user.getUsername());
    	if(localUser != null) {
    		LOG.info("user {} already exists. Nothing will be done.", user.getUsername());
    	}else {
    		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    		user.setRoles(new HashSet<>(roleRepository.findAll()));
    		userRepository.save(user);
    		
    		ShoppingCart shoppingCart = new ShoppingCart();
    		shoppingCart.setUser(user);
    		user.setShoppingCart(shoppingCart);
    		
    		user.setUserShippingList(new ArrayList<UserShipping>());
    		localUser = userRepository.save(user);
    	}
		return localUser;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

	@Override
	public User addUser(User user, Set<Role> role) throws Exception {
		User localUser = userRepository.findByUsername(user.getUsername());
    	if(localUser != null) {
    		LOG.info("user {} already exists. Nothing will be done.", user.getUsername());
    	}else {
    		for (Role ur : role) {
				roleRepository.findByname(ur.getName());
			}
    		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    		user.setRoles(new HashSet<>(roleRepository.findAll()));
    		user.getRoles().addAll(role);
    		userRepository.save(user);
    		
    		ShoppingCart shoppingCart = new ShoppingCart();
    		shoppingCart.setUser(user);
    		user.setShoppingCart(shoppingCart);
    		
    		user.setUserShippingList(new ArrayList<UserShipping>());
    		localUser = userRepository.save(user);
    	}
		return localUser;
	}
}
