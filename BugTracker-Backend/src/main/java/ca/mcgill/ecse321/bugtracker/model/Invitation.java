package ca.mcgill.ecse321.bugtracker.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

// line 42 "../../../../BugTracker-Backend.ump"
@Entity
public class Invitation
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum InvitationStatus { NewInvite, Denied, Accepted }

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Invitation Attributes
  private InvitationStatus invStatus;

  // @Id
  // @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
  //Invitation Associations
  private UserRole sender;
  private UserRole receiver;
  private Project project;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Invitation(){

  }

  public Invitation(InvitationStatus aInvStatus, UserRole sender, UserRole receiver, Project aProject)
  {
    invStatus = aInvStatus;
    // id = aId;
    this.sender = sender;
    this.receiver = receiver;
    this.project = aProject;
    // boolean didAddUserRole = setUserRole(aUserRole);
    // if (!didAddUserRole)
    // {
    //   throw new RuntimeException("Unable to create invitation due to userRole");
    // }
    // boolean didAddProject = setProject(aProject);
    // if (!didAddProject)
    // {
    //   throw new RuntimeException("Unable to create invitation due to project");
    // }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setInvStatus(InvitationStatus aInvStatus)
  {
    boolean wasSet = false;
    invStatus = aInvStatus;
    wasSet = true;
    return wasSet;
  }

  public void setId(int aId)
  {
    this.id = aId;
    // boolean wasSet = false;
    // id = aId;
    // wasSet = true;
    // return wasSet;
  }

  public InvitationStatus getInvStatus()
  {
    return invStatus;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  public int getId()
  {
    return id;
  }
  /* Code from template association_GetOne */
  @ManyToOne (optional = false)
  public UserRole getSender()
  {
    return sender;
  }

  @ManyToOne (optional = false)
  public UserRole getReceiver()
  {
    return receiver;
  }

  /* Code from template association_GetOne */
  @ManyToOne (optional = false)
  public Project getProject()
  {
    return project;
  }
  /* Code from template association_SetOneToMany */
  public void setSender(UserRole sender)
  {
    this.sender = sender;
  }

  public void setReceiver(UserRole receiver)
  {
    this.receiver = receiver;
  }
  // public boolean setUserRole(UserRole aUserRole)
  // {
  //   boolean wasSet = false;
  //   if (aUserRole == null)
  //   {
  //     return wasSet;
  //   }

  //   UserRole existingUserRole = userRole;
  //   userRole = aUserRole;
  //   if (existingUserRole != null && !existingUserRole.equals(aUserRole))
  //   {
  //     existingUserRole.removeInvitation(this);
  //   }
  //   userRole.addInvitation(this);
  //   wasSet = true;
  //   return wasSet;
  // }

  /* Code from template association_SetOneToMany */
  public void setProject(Project aProject)
  {
    this.project = aProject;
    // boolean wasSet = false;
    // if (aProject == null)
    // {
    //   return wasSet;
    // }

    // Project existingProject = project;
    // project = aProject;
    // if (existingProject != null && !existingProject.equals(aProject))
    // {
    //   existingProject.removeInvitation(this);
    // }
    // project.addInvitation(this);
    // wasSet = true;
    // return wasSet;
  }

  public void delete()
  {
    UserRole placeholderSender = sender;
    this.sender = null;
    if(placeholderSender != null)
    {
      placeholderSender.removeInvitation(this);
    }

    UserRole placeholderReceiver = receiver;
    this.receiver = null;
    if(placeholderReceiver != null)
    {
      placeholderReceiver.removeInvitation(this);
    }

    Project placeholderProject = project;
    this.project = null;
    if(placeholderProject != null)
    {
      placeholderProject.removeInvitation(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "id" + ":" + getId()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "invStatus" + "=" + (getInvStatus() != null ? !getInvStatus().equals(this)  ? getInvStatus().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "sender = "+(getSender()!=null?Integer.toHexString(System.identityHashCode(getSender())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "receiver = "+(getReceiver()!=null?Integer.toHexString(System.identityHashCode(getReceiver())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "project = "+(getProject()!=null?Integer.toHexString(System.identityHashCode(getProject())):"null");
  }
}