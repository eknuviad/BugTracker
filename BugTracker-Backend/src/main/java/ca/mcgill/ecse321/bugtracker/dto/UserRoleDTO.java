package ca.mcgill.ecse321.bugtracker.dto;

import java.util.List;

import ca.mcgill.ecse321.bugtracker.model.Account;
import ca.mcgill.ecse321.bugtracker.model.Invitation;
import ca.mcgill.ecse321.bugtracker.model.Project;
import ca.mcgill.ecse321.bugtracker.model.Ticket;

public class UserRoleDTO {

    private String userName;

    //UserRole Associations
    private Account acc;
    private List<Project> projects;
    private List<Invitation> invitations;
    private List<Ticket> tickets;

    public UserRoleDTO (String userName, Account acc, List<Project> projects, List<Invitation> invitations, List<Ticket> tickets){
        this.userName = userName;
        this.acc = acc;
        this.projects = projects;
        this.invitations = invitations;
        this.tickets = tickets;
    }
  
    
}