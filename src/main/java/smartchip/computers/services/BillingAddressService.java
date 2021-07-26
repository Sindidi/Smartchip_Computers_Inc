package smartchip.computers.services;

import smartchip.computers.entities.BillingAddress;
import smartchip.computers.entities.UserBilling;

public interface BillingAddressService {
	BillingAddress setByUserBilling(UserBilling userBilling, BillingAddress billingAddress);
}
