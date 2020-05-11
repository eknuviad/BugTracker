package ca.mcgill.ecse321.bugtracker.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.Month;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.bugtracker.model.Account;
import ca.mcgill.ecse321.bugtracker.model.Comment;
import ca.mcgill.ecse321.bugtracker.model.Manager;
import ca.mcgill.ecse321.bugtracker.model.Project;
import ca.mcgill.ecse321.bugtracker.model.Ticket;
import ca.mcgill.ecse321.bugtracker.model.Ticket.TicketStatus;

import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CommentTest {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ManagerRepository managerRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private UserRoleRepository userRepository;

    private String accName = "He";
    private String email = "ziruiHe@gmail.com";
    private String desc = "Testing my account";
    private int phoneNum = 12345;
    private String pass = "test12345";
    private String userName = "ziruiHe";

    //Ticket data
    private String tickDesc = "First ticket issued";
    private Date strtdate = java.sql.Date.valueOf(LocalDate.of(2020, Month.JANUARY, 28));
    private Date enddate = java.sql.Date.valueOf(LocalDate.of(2020, Month.JANUARY, 31));
    
    @AfterEach
    public void clearDatabase() {
        commentRepository.deleteAll();
        ticketRepository.deleteAll();
        projectRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    public void testInvitation(){
        Account acc = new Account (accName, email, desc, phoneNum);
        accountRepository.save(acc);

        Manager manager = new Manager();
        manager.setUser(acc);
        manager.setPassword(pass);
        manager.setUserName(userName);
        
        managerRepository.save(manager); 

        Project proj = new Project();
        proj.setName("projName");
        proj.setUserRole(manager);

        projectRepository.save(proj);

        Ticket ticket = new Ticket();
        ticket.setTicketStatus(TicketStatus.Todo);
        ticket.setDescription(tickDesc);
        ticket.setStartDate(strtdate);
        ticket.setEndDate(enddate);
        ticket.setUserRole(manager);
        ticket.setP(proj);

        ticketRepository.save(ticket);

        Comment comment = new Comment();
        comment.setMessage("aMessage");
        comment.setTicket(ticket);
        comment.setUserRole(manager);
        long now = System.currentTimeMillis();
        Time sqlTime = new Time(now);
        comment.setTimeStamp(sqlTime);
        commentRepository.save(comment);

        comment = null;
        comment = commentRepository.findCommentByTimeStampAndTicket(sqlTime, ticket);
        assertNotNull(comment);
        // assertEquals(sqlTime.getSeconds(), comment.getTimeStamp().getSeconds());
        assertEquals(tickDesc, comment.getTicket().getDescription());
        assertEquals(manager.getUserName(), comment.getUserRole().getUserName());
        
    }    
    
}