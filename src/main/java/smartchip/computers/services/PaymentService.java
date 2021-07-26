package smartchip.computers.services;

import smartchip.computers.entities.Payment;
import smartchip.computers.entities.UserPayment;

public interface PaymentService {
	Payment setByUserPayment(UserPayment userPayment, Payment payment);
}
