package smartchip.computers.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import smartchip.computers.entities.UserShipping;
import smartchip.computers.repository.UserShippingRepository;
@Service
public class UserShippingServiceImpl implements UserShippingService{
	
	@Autowired
	private UserShippingRepository userShippingRepository;
	
	
	public UserShipping findById(Long id) {
		return userShippingRepository.findById(id).orElse(null);
	}
	
	public void deleteById(Long id) {
		userShippingRepository.deleteById(id);
	}

}
