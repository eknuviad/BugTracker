package ca.mcgill.ecse321.bugtracker.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.bugtracker.model.*;

public interface InvitationRepository extends CrudRepository<Invitation, Integer>{

	//boolean existsByProjectAndUserRole(Project prjectName, UserRole userRoleName);

	Invitation findByProjectAndReceiver(Project projectName, UserRole receiver);

	Invitation findById(int id);

	List<Invitation> findAllByReceiver(UserRole receiver);

	List<Invitation> findAllByProject(Project project);

}