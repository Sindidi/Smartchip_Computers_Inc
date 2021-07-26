package smartchip.computers.repository;

import org.springframework.data.repository.CrudRepository;

import smartchip.computers.entities.UserPayment;


public interface UserPaymentRepository extends CrudRepository<UserPayment, Long>{

}
