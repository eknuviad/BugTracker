package ca.mcgill.ecse321.bugtracker.dao;

import java.sql.Date;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse321.bugtracker.model.Ticket;

@Repository
public interface TicketRepository extends CrudRepository<Ticket, Integer> {

	Ticket findTicketByStartDate(Date strtdate);

	Ticket findTicketById(int id);
    
}