package ca.mcgill.ecse321.bugtracker.dao;
import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.bugtracker.model.Invitation;

public interface InvitationRepository extends CrudRepository<Invitation, String>{

	//Invitation findAccountByName(String name);

}