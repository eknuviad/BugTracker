package ca.mcgill.ecse321.bugtracker.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse321.bugtracker.model.Ticket;

@Repository
public interface TicketRepository extends CrudRepository<Ticket, Integer> {
    
}