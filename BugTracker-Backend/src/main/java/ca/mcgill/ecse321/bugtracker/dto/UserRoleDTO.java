package ca.mcgill.ecse321.bugtracker.dto;

import java.util.List;

import ca.mcgill.ecse321.bugtracker.model.Account;

public class UserRoleDTO {

    private String userName;

    //UserRole Associations
    private Account acc;
    private List<ProjectDTO> projects;
    private List<InvitationDTO> invitations;
    private List<TicketDTO> tickets;

    public UserRoleDTO (String userName, Account acc, List<ProjectDTO> projects, List<InvitationDTO> invitations, List<TicketDTO> tickets){
        this.userName = userName;
        this.acc = acc;
        this.projects = projects;
        this.invitations = invitations;
        this.tickets = tickets;
    }
  
    
}