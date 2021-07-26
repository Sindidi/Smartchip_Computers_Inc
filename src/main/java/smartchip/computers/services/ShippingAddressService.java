package smartchip.computers.services;

import smartchip.computers.entities.ShippingAddress;
import smartchip.computers.entities.UserShipping;

public interface ShippingAddressService {
	ShippingAddress setByUserShipping(UserShipping userShipping, ShippingAddress shippingAddress);
}
