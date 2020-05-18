package ca.mcgill.ecse321.bugtracker.dto;

import ca.mcgill.ecse321.bugtracker.model.UserRole;
import ca.mcgill.ecse321.bugtracker.model.Invitation.InvitationStatus;
import ca.mcgill.ecse321.bugtracker.service.InvitationService;

public class InvitationDTO {

  private InvitationStatus invStatus;
  private int id;
  //Invitation Associations
  private UserRoleDTO userRole;
  //private Project project;

  public InvitationDTO (InvitationStatus status, UserRoleDTO role, int id){
      this.invStatus = status;
      this.userRole = role;
      this.id = id;
  }

  public InvitationStatus getInvStatus() {
      return invStatus;
  }

  public void setInvStatus(InvitationStatus invStatus) {
      this.invStatus = invStatus;
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
    
}