package ca.mcgill.ecse321.bugtracker.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.bugtracker.model.*;

public interface InvitationRepository extends CrudRepository<Invitation, Integer>{

	// Invitation findInvitationById(int id);

	List<Invitation> findByProject(Project projectName);

	boolean existsByProjectAndUserRole(Project prjectName, UserRole userRoleName);

	Invitation findByProjectAndUserRole(Project projectName, UserRole userRoleName);

	Invitation findById(int id);

	List<Invitation> findAllByUserRole(UserRole role);

	List<Invitation> findAllByProject(Project project);

}