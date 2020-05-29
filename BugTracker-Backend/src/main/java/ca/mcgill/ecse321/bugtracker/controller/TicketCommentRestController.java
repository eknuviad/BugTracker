package ca.mcgill.ecse321.bugtracker.controller;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.bugtracker.dto.AccountDTO;
import ca.mcgill.ecse321.bugtracker.dto.CommentDTO;
import ca.mcgill.ecse321.bugtracker.dto.InvitationDTO;
import ca.mcgill.ecse321.bugtracker.dto.ProjectDTO;
import ca.mcgill.ecse321.bugtracker.dto.TicketDTO;
import ca.mcgill.ecse321.bugtracker.dto.UserRoleDTO;
import ca.mcgill.ecse321.bugtracker.model.Account;
import ca.mcgill.ecse321.bugtracker.model.Admin;
import ca.mcgill.ecse321.bugtracker.model.Comment;
import ca.mcgill.ecse321.bugtracker.model.Developer;
import ca.mcgill.ecse321.bugtracker.model.Invitation;
import ca.mcgill.ecse321.bugtracker.model.Manager;
import ca.mcgill.ecse321.bugtracker.model.Project;
import ca.mcgill.ecse321.bugtracker.model.Ticket;
import ca.mcgill.ecse321.bugtracker.model.UserRole;
import ca.mcgill.ecse321.bugtracker.model.Ticket.TicketStatus;
import ca.mcgill.ecse321.bugtracker.service.AccountService;
import ca.mcgill.ecse321.bugtracker.service.CommentService;
import ca.mcgill.ecse321.bugtracker.service.ProjectService;
import ca.mcgill.ecse321.bugtracker.service.TicketService;
import ca.mcgill.ecse321.bugtracker.service.UserRoleService;

@CrossOrigin(origins = "*")
@RestController
public class TicketCommentRestController {

    @Autowired
    private ProjectService pService;

    @Autowired
    private UserRoleService urService;

    @Autowired
    private TicketService tService;

    @Autowired
    private CommentService cService;

    //================= Ticket Rest Controller================================================
    @PostMapping({ "/create/ticket", "/create/ticket/" })
    public TicketDTO createTicket(@RequestParam("ticketStatus") TicketStatus status,
            @RequestParam("description") String description, @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate, @RequestParam("userName") String userName,
            @RequestParam("projectId") int pId) throws IllegalArgumentException, ParseException {

        Project project = pService.getProjectById(pId);
        if (project == null){
            throw new IllegalArgumentException("Project dosen't exists.");
        }
        UserRole ur = urService.getUserRoleByUserName(userName);
        if (ur == null){
            throw new IllegalArgumentException("UserRole dosen't exists.");
        }
        //DateFormat format = new SimpleDateFormat("yyyy/mm/dd", Locale.ENGLISH);
        Date sDate = Date.valueOf(startDate);
        Date eDate = Date.valueOf(endDate);
        Ticket ticket = tService.createTicket(status, description, sDate, eDate, ur, project);
        return convertTicketToDTO(ticket);

    }

    @PostMapping({"/update/ticket", "/update/ticket/"})
    public TicketDTO updateTicket(@RequestParam("ticketId") int tId, @RequestParam("newtStatus") TicketStatus newtStatus, @RequestParam("newtDesc") String newtDesc,
                                    @RequestParam("newstrtDate") Date newstrtDate, @RequestParam("newendDate") Date newendDate)
                                     throws IllegalArgumentException{

        Ticket ticket = tService.getTicketById(tId);
        if (ticket == null){
            throw new IllegalArgumentException("Ticket dosen't exists.");
        }
        ticket = tService.updateTicket(ticket, newtStatus, newtDesc, newstrtDate, newendDate);
        return convertTicketToDTO(ticket);

    }

    @GetMapping({"/ticket/userrole", "/ticket/userrole/"})
    public List<TicketDTO> getAllTicketsbyUserRole(@RequestParam("userName") String userName) throws IllegalArgumentException{
    
        List<Ticket> tickets = tService.getAllTicketsByUserRole(userName);
        List<TicketDTO> tDto = new ArrayList<>();
        if (tickets == null) {
            tDto = null;
        } else {
            for (Ticket t: tickets){
                tDto.add(convertTicketToDTO(t));
            }
        }
        return tDto;
    }    

    @GetMapping({"/ticket/project", "/ticket/project/"})
    public List<TicketDTO> getAllTicketsbyProject(@RequestParam("projectId") int pId) throws IllegalArgumentException{
    
        List<Ticket> tickets = tService.getAllTicketsByProject(pId);
        List<TicketDTO> tDto = new ArrayList<>();
        if (tickets == null) {
            tDto = null;
        } else {
            for (Ticket t: tickets){
                tDto.add(convertTicketToDTO(t));
            }
        }
        return tDto;
    }  
    
    //================= Comment Rest Controller================================================
    @PostMapping({"create/comment", "create/comment/"})
    public CommentDTO creatComment(@RequestParam("message") String message, @RequestParam("userName") String userName, 
                                    @RequestParam("ticketId") int tId) throws IllegalArgumentException{

        UserRole ur = urService.getUserRoleByUserName(userName);
        if (ur == null){
            throw new IllegalArgumentException("UserRole dosen't exists.");
        }
        Ticket ticket = tService.getTicketById(tId);
        if (ticket == null){
            throw new IllegalArgumentException("Ticket dosen't exists.");
        }
        Comment comment = cService.createComment(message, ur, ticket);
        return convertCommentToDTO(comment);
    }


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

    private TicketDTO convertTicketToDTO (Ticket ticket){
        if (ticket == null){
            throw new IllegalArgumentException("There is no ticket.");
        }

        TicketDTO tDto = new TicketDTO(ticket.getTicketStatus(), ticket.getDescription(), ticket.getStartDate(), ticket.getEndDate(), ticket.getId());
        return tDto;
    }

    private InvitationDTO converInvitationToDTO (Invitation invitation){
        if (invitation == null){
            throw new IllegalArgumentException("There is no invitation.");
        }
        
        UserRoleDTO urDto = convertToDTO (invitation.getReceiver());
        InvitationDTO invDto = new InvitationDTO(invitation.getInvStatus(), urDto, invitation.getId());
        return invDto;
    }

    private CommentDTO convertCommentToDTO (Comment comment){
        if (comment == null){
            throw new IllegalArgumentException("There is no comment.");            
        }

        UserRoleDTO urDto = convertToDTO(comment.getUserRole());
        TicketDTO tDto = convertTicketToDTO(comment.getTicket());
        CommentDTO cDto = new CommentDTO(comment.getTimeStamp(), comment.getMessage(), urDto, tDto, comment.getId());
        return cDto;
    }

    private ProjectDTO converProjectToDTO (Project project){
        if (project == null){
            throw new IllegalArgumentException("There is no project.");
        }

        List<Invitation> invitations = project.getInvitations();
        List<Ticket> tickets = project.getTickets();
        List<InvitationDTO> invDTO = new ArrayList<>();
        List<TicketDTO> tDTO = new ArrayList<>();

         if (invitations == null){
             invDTO = null;
         } else {
            for( Invitation inv : invitations){
                invDTO.add(converInvitationToDTO(inv));
            }
         }

         if (tickets == null){
            tDTO = null;
        } else {
           for( Ticket t : tickets){
               tDTO.add(convertTicketToDTO(t));
           }
        }
        
        ProjectDTO pDto = new ProjectDTO(project.getName(), project.getId(), convertToDTO(project.getUserRole()), invDTO, tDTO);
        return pDto;
    }
}