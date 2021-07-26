package smartchip.computers.services;

import org.springframework.stereotype.Service;

import smartchip.computers.entities.Payment;
import smartchip.computers.entities.UserPayment;

@Service
public class PaymentServiceImpl implements PaymentService{
	
	public Payment setByUserPayment(UserPayment userPayment, Payment payment) {
		
		payment.setType(userPayment.getType());
		payment.setHolderName(userPayment.getHolderName());
		payment.setCardNumber(userPayment.getCardNumber());
		payment.setExpiryMonth(userPayment.getExpiryMonth());
		payment.setExpiryYear(userPayment.getExpiryYear());
		payment.setCvc(userPayment.getCvc());
		
		return payment;
	}

}
