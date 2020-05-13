package ca.mcgill.ecse321.bugtracker.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse321.bugtracker.model.Manager;
import ca.mcgill.ecse321.bugtracker.model.UserRole;

@Repository
public interface ManagerRepository extends CrudRepository<Manager, String> {

	Manager findManagerByUserName(String userName);

	boolean existsByUserName(String usrName);

	UserRole findByUserEmail(String email);
    
}