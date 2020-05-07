package ca.mcgill.ecse321.bugtracker.dao;
import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.bugtracker.model.Developer;

public interface DeveloperRepository extends CrudRepository<Developer, String>{

	//Developer findAccountByName(String name);

}