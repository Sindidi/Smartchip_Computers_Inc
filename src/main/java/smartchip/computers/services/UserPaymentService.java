package smartchip.computers.services;

import smartchip.computers.entities.UserPayment;

public interface UserPaymentService {
	UserPayment findById(Long id);
	void deteteById(Long id);
}
