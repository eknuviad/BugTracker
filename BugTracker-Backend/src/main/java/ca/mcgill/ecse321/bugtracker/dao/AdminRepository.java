package ca.mcgill.ecse321.bugtracker.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.bugtracker.model.Admin;
import ca.mcgill.ecse321.bugtracker.model.UserRole;

public interface AdminRepository extends CrudRepository<Admin, String>{

	boolean existsByUserName(String usrName);

	UserRole findByUserEmail(String email);

	UserRole findAdminByUserName(String usrName);

	//Admin findAccountByName(String name);

}