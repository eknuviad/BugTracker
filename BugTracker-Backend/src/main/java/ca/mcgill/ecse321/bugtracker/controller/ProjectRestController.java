package ca.mcgill.ecse321.bugtracker.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.bugtracker.dto.InvitationDTO;
import ca.mcgill.ecse321.bugtracker.dto.ProjectDTO;
import ca.mcgill.ecse321.bugtracker.dto.TicketDTO;
import ca.mcgill.ecse321.bugtracker.dto.UserRoleDTO;
import ca.mcgill.ecse321.bugtracker.model.Admin;
import ca.mcgill.ecse321.bugtracker.model.Invitation;
import ca.mcgill.ecse321.bugtracker.model.Manager;
import ca.mcgill.ecse321.bugtracker.model.Project;
import ca.mcgill.ecse321.bugtracker.model.Ticket;
import ca.mcgill.ecse321.bugtracker.model.UserRole;
import ca.mcgill.ecse321.bugtracker.service.ProjectService;
import ca.mcgill.ecse321.bugtracker.service.UserRoleService;


@CrossOrigin(origins = "*")
@RestController
public class ProjectRestController {

    @Autowired 
    private ProjectService pService;

    @Autowired 
    private UserRoleService uService;

    @PostMapping({"/create/project", "/create/project/"})
    public ProjectDTO createProjectByUserRole (@RequestParam("projectName") String pName, @RequestParam("userName") String userName) throws IllegalArgumentException{
        UserRole role = uService.getUserRoleByUserName(userName);
        if (role == null) {
            throw new IllegalArgumentException("There is no userrole.");
        }
        Project project = pService.createProjectByUserRole(pName, role);
        ProjectDTO pDto = converProjectToDTO(project);
        return pDto;
    }

    @GetMapping({"/projects/userrole", "/projects/userrole/"})
    public List<ProjectDTO> getProjectsByUserRole(@RequestParam("userName") String userName) throws IllegalArgumentException{
        UserRole role = uService.getUserRoleByUserName(userName);
        if (role == null) {
            throw new IllegalArgumentException("There is no userrole.");
        }
        List<Project> projects = pService.getAllProjectsByUserRole(role);
        List<ProjectDTO> pDTOs = new ArrayList<>();
        if (projects == null){
            pDTOs = null; 
        } else {
            for (Project p: projects){
                pDTOs.add(converProjectToDTO(p));
            }
        }
        return pDTOs;
    }

    @GetMapping({"/project/id", "/projects/id/"})
    public ProjectDTO getProjectById(@RequestParam("id") int id) throws IllegalArgumentException{
        Project project = pService.getProjectById(id);
        if (project == null){
            throw new IllegalArgumentException("There is no project.");
        }
        return converProjectToDTO(project);
    }

    @PostMapping({"/update/project", "/update/project/"})
    public ProjectDTO updateProjectName (@RequestParam("newProjectName") String newName, @RequestParam("id") int id) throws IllegalArgumentException{
        Project project = pService.getProjectById(id);
        if (project == null){
            throw new IllegalArgumentException("There is no project.");
        }
        project = pService.updateProjectName(project, newName);
        ProjectDTO pDto = converProjectToDTO(project);
        return pDto;
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
        
        UserRoleDTO urDto = convertToDTO (invitation.getUserRole());
        InvitationDTO invDto = new InvitationDTO(invitation.getInvStatus(), urDto, invitation.getId());
        return invDto;
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