package ca.mcgill.ecse321.bugtracker.dao;

import java.sql.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse321.bugtracker.model.Project;
import ca.mcgill.ecse321.bugtracker.model.Ticket;
import ca.mcgill.ecse321.bugtracker.model.UserRole;

@Repository
public interface TicketRepository extends CrudRepository<Ticket, Integer> {

	Ticket findTicketByStartDate(Date strtdate);

	Ticket findTicketById(int id);

	List<Ticket> findByUserRole(UserRole ur);

	List<Ticket> findTicketByPId(int pId);
    
}