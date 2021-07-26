package smartchip.computers.services;

import smartchip.computers.entities.UserShipping;

public interface UserShippingService {
	UserShipping findById(Long id);
	
	void deleteById(Long id);
}
