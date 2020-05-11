package ca.mcgill.ecse321.bugtracker.dao;
import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.bugtracker.model.Developer;
import ca.mcgill.ecse321.bugtracker.model.UserRole;

public interface DeveloperRepository extends CrudRepository<Developer, String>{

	boolean existsByUserName(String usrName);

	UserRole findByUserEmail(String email);

	//Developer findAccountByName(String name);

}