package ca.mcgill.ecse321.ParkingManagement.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.ParkingManagement.model.Account;

public interface AccountRepository extends CrudRepository<Account, String> {
	Account findAccountByEmail(String email);
	boolean existsByEmail(String email);
	Account findAccountByEmailAndPassword(String email, String password);
	boolean existsAccountByEmailAndPassword(String email, String password);
	void deleteAccountByEmailAndPassword(String email, String password);
}
