package ca.mcgill.ecse321.bugtracker.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse321.bugtracker.model.Manager;

@Repository
public interface ManagerRepository extends CrudRepository<Manager, String> {

	Manager findManagerByUserName(String userName);
    
}