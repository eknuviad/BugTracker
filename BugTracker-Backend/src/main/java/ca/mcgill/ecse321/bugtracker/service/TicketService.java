package ca.mcgill.ecse321.bugtracker.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.bugtracker.dao.TicketRepository;
import ca.mcgill.ecse321.bugtracker.model.Project;
import ca.mcgill.ecse321.bugtracker.model.Ticket;
import ca.mcgill.ecse321.bugtracker.model.UserRole;

@Service
public class TicketService {
    
    @Autowired
    private TicketRepository tRepository;

    @Transactional
    public Ticket createTicket(Ticket.TicketStatus tStatus, String tDesc, Date strtDate, Date endDate, UserRole ur, Project p){
        String error = "";
        if (strtDate == null) {
            error = error + "TIcket start date cannot be empty.";
        }
        if (endDate == null) {
            error = error + "Ticket end date cannot be empty.";
        }
        if (tStatus == null){
            error = error + "Ticket status cannot be empty.";
        }
        if (ur == null) {
            error = error + "User role cannot be empty to create a ticket.";
        }
        if (p == null){
            error = error + "Project cannot be empty to create a ticket.";
        }
        if (tDesc == null || tDesc.trim().length() == 0) {
            error = error + "The ticket description cannot be empty or have spaces.";
        }
        if (error.length() > 0) {
            throw new IllegalArgumentException(error);
        }
        Ticket t = new Ticket();
        t.setTicketStatus(tStatus);
        t.setDescription(tDesc);
        t.setStartDate(strtDate);
        t.setEndDate(endDate);
        t.setUserRole(ur);
        t.setP(p);

        tRepository.save(t);

        return t;

    }

    /**
     * This gets all the tickets in the system.
     * @return
     */
    @Transactional
    public List<Ticket> getAllTickets(){
        return toList(tRepository.findAll());
    }
    
    @Transactional
    public Ticket getTicketById(int id){
        Ticket t = tRepository.findTicketById(id);
        if (t == null) {
            throw new IllegalArgumentException("No such ticket exists.");
        }
        return t;
    }

    @Transactional
    public void deleteTicketById(int id){
        Ticket t = tRepository.findTicketById(id);
        if (t == null) {
            throw new IllegalArgumentException("No such ticket exists.");
        }
        tRepository.delete(t);
    }

       /**
     *
     * @param iterable
     * @param <T>
     * @return
     */
    private <T> List<T> toList(Iterable<T> iterable) {
        List<T> resultList = new ArrayList<T>();
        for (T t : iterable) {
            resultList.add(t);
        }
        return resultList;
    }

}