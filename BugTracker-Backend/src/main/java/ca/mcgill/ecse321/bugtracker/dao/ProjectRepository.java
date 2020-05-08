package ca.mcgill.ecse321.bugtracker.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse321.bugtracker.model.Project;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Integer> {

	Project findProjectByName(String projName);
    
}