package smartchip.computers.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import smartchip.computers.entities.UserPayment;
import smartchip.computers.repository.UserPaymentRepository;


@Service
public class UserPaymentServiceImpl implements UserPaymentService{

	@Autowired
	private UserPaymentRepository userPaymentRepository;
		
	public UserPayment findById(Long id) {
		return userPaymentRepository.findById(id).orElse(null);
	}
	
	@Override
	public void deteteById(Long id) {
		userPaymentRepository.deleteById(id);	
	}
} 
