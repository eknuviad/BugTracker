package ca.mcgill.ecse321.bugtracker.dao;
import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.bugtracker.model.Invitation;

public interface InvitationRepository extends CrudRepository<Invitation, Integer>{

	Invitation findInvitationById(Integer id);

}