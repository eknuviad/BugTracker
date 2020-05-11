package ca.mcgill.ecse321.bugtracker.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse321.bugtracker.model.UserRole;

@Repository
public interface UserRoleRepository extends CrudRepository<UserRole, String>{

	UserRole findByPasswordAndUserName(String password, String userName);

	// boolean existsByUsername(String usrName);

	// List<UserRole> findAllByUserEmail(String email);
    
}