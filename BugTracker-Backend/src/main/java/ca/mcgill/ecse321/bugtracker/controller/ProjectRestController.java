package ca.mcgill.ecse321.bugtracker.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.bugtracker.dto.AccountDTO;
import ca.mcgill.ecse321.bugtracker.dto.InvitationDTO;
import ca.mcgill.ecse321.bugtracker.dto.TicketDTO;
import ca.mcgill.ecse321.bugtracker.dto.UserRoleDTO;
import ca.mcgill.ecse321.bugtracker.model.Account;
import ca.mcgill.ecse321.bugtracker.model.Admin;
import ca.mcgill.ecse321.bugtracker.model.Developer;
import ca.mcgill.ecse321.bugtracker.model.Invitation;
import ca.mcgill.ecse321.bugtracker.model.Manager;
import ca.mcgill.ecse321.bugtracker.model.Ticket;
import ca.mcgill.ecse321.bugtracker.model.UserRole;
import ca.mcgill.ecse321.bugtracker.service.AccountService;
import ca.mcgill.ecse321.bugtracker.service.UserRoleService;
import ca.mcgill.ecse321.bugtracker.model.Project.TicketStatus;


@CrossOrigin(origins = "*")
@RestController
public class ProjectRestController {

    private UserRoleDTO convertToDTO( UserRole ur){
        if (ur == null) {
            throw new IllegalArgumentException("There is no userrole.");
        }
        UserRoleDTO urDTO;
        String username;
        if(ur instanceof Manager){
            username = ur.getDisplayName();
        }else if (ur instanceof Admin){
             username = ur.getDisplayName();
        }else{
            username = ur.getDisplayName(); 
        }
        urDTO = new UserRoleDTO (username, ur.getUser().getEmail());
        return urDTO;
    }

    private TicketDTO convertToDTO (Ticket ticket){
        if (ticket == null){
            throw new IllegalArgumentException("There is no ticket.");
        }

        TicketDTO tDto = new TicketDTO(ticket.getTicketStatus(), ticket.getDescription(), ticket.getStartDate(), ticket.getEndDate(), ticket.getId());
        return tDto;
    }

    private InvitationDTO converToDTO (Invitation invitation){
        if (invitation == null){
            throw new IllegalArgumentException("There is no invitation.");
        }
        
        UserRoleDTO urDto = convertToDTO (invitation.getUserRole());
        InvitationDTO invDto = new InvitationDTO(invitation.getInvStatus(), urDto, invitation.getId());
        return invDto;
    }
}