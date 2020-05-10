package ca.mcgill.ecse321.bugtracker.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import ca.mcgill.ecse321.bugtracker.model.Account;
import ca.mcgill.ecse321.bugtracker.model.Manager;
import ca.mcgill.ecse321.bugtracker.model.Project;
import ca.mcgill.ecse321.bugtracker.model.Ticket;
import ca.mcgill.ecse321.bugtracker.model.Ticket.TicketStatus;

@SpringBootTest
public class TicketTest {
    @Autowired
    private ManagerRepository managerRepository;
    
    @Autowired
    private AccountRepository accRepository;

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private TicketRepository ticketRepository;

    //account data
    private String accName = "Doe";
    private String email = "johnDoe@gmail.com";
    private String desc = "Testing my john doe account";
    private int phoneNum = 123;
    //manager data
    private String pass = "test123";
    private String userName = "johnDoe";
    //project data
    private String projName = "TestProject";
    //Ticket data
    private String tickDesc = "First ticket issued";
    private Date strtdate = java.sql.Date.valueOf(LocalDate.of(2020, Month.JANUARY, 28));
    private Date enddate = java.sql.Date.valueOf(LocalDate.of(2020, Month.JANUARY, 31));
    
    @AfterEach
    public void clearDatabase(){  
        ticketRepository.deleteAll();
        projectRepository.deleteAll();     
        managerRepository.deleteAll();
        accRepository.deleteAll();
        
    }

    /**
     * Ticket persistence test
     */

     @Test
     public void persistAndLoadTicket(){
        Account acc = new Account (accName, email, desc, phoneNum);
        accRepository.save(acc);

        Manager manager = new Manager();
        manager.setUser(acc);
        manager.setPassword(pass);
        manager.setUserName(userName);
        
        managerRepository.save(manager); 

        Project proj = new Project();
        proj.setName(projName);
        proj.setUserRole(manager);

        projectRepository.save(proj);

        Ticket tick = new Ticket();
        tick.setTicketStatus(TicketStatus.Todo);
        tick.setDescription(tickDesc);
        tick.setStartDate(strtdate);
        tick.setEndDate(enddate);
        tick.setUserRole(manager);
        tick.setP(proj);

        ticketRepository.save(tick);

        tick = null;

        tick = ticketRepository.findTicketByStartDate(strtdate);
        assertNotNull(tick);
        assertEquals(TicketStatus.Todo, tick.getTicketStatus());
        assertEquals (strtdate, tick.getStartDate());
        assertEquals (tickDesc, tick.getDescription());
    

     }
}