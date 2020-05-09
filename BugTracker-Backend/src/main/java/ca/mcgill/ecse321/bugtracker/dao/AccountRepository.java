package ca.mcgill.ecse321.bugtracker.dao;
import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.bugtracker.model.Account;

public interface AccountRepository extends CrudRepository<Account, String>{

	Account findAccountByEmail(String email);

	//Account findAccountByName(String name);

}