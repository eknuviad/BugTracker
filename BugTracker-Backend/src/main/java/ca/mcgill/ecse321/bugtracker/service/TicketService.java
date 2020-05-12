package ca.mcgill.ecse321.bugtracker.service;

import java.sql.Date;

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
}