package ca.mcgill.ecse321.bugtracker.dto;

import java.util.List;

import org.omg.CORBA.portable.IDLEntity;

import ca.mcgill.ecse321.bugtracker.model.Invitation;
import ca.mcgill.ecse321.bugtracker.model.Ticket;

public class ProjectDTO {
    private String name;
    private int id;
  
    //Project Associations
    private UserRoleDTO userRole;
    private List<InvitationDTO> invitations;
    private List<TicketDTO> tickets;

    public ProjectDTO (String name, int id, UserRoleDTO role, 
                        List<InvitationDTO> invitations, List<TicketDTO> tickets){
        this.name = name;
        this.id = id;
        this.userRole = role;
        this.invitations = invitations;
        this.tickets = tickets;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserRoleDTO getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRoleDTO userRole) {
        this.userRole = userRole;
    }

    public List<InvitationDTO> getInvitations() {
        return invitations;
    }

    public void setInvitations(List<InvitationDTO> invitations) {
        this.invitations = invitations;
    }

    public List<TicketDTO> getTickets() {
        return tickets;
    }

    public void setTickets(List<TicketDTO> tickets) {
        this.tickets = tickets;
    }
}