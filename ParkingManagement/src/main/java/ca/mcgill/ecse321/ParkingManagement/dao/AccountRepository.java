package ca.mcgill.ecse321.ParkingManagement.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse321.ParkingManagement.model.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, String> {
	Account findAccountByEmail(String email);
	boolean existsByEmail(String email);
}
