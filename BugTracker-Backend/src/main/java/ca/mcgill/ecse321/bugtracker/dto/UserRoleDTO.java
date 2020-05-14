package ca.mcgill.ecse321.bugtracker.dto;

import java.util.List;

import ca.mcgill.ecse321.bugtracker.model.Account;

public class UserRoleDTO {

    private String userName;
    private String email;

    //UserRole Associations
    //private Account acc;
    // private List<ProjectDTO> projects;
    // private List<InvitationDTO> invitations;
    // private List<TicketDTO> tickets;

    public UserRoleDTO (String userName, String email){
        this.userName = userName;
        this.email = email;
    }
  
    
}