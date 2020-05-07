package ca.mcgill.ecse321.bugtracker.dao;
import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.bugtracker.model.Admin;

public interface AdminRepository extends CrudRepository<Admin, String>{

	//Admin findAccountByName(String name);

}