package ca.mcgill.ecse321.bugtracker.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse321.bugtracker.model.Project;
import ca.mcgill.ecse321.bugtracker.model.UserRole;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Integer> {

	Project findProjectByName(String projName);

	List<Project> findAllByUserRole(UserRole ur);

	boolean existsByName(String pName);

	Project findProjectById(int id);
    
}