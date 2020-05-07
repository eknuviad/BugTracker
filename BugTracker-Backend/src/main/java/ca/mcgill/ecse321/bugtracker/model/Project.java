package ca.mcgill.ecse321.bugtracker.model;

import java.util.*;

import javax.persistence.Entity;

// line 33 "../../../../BugTracker-Backend.ump"
@Entity
public class Project
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum InvitationStatus { NewInvite, Denied, Accepted }
  public enum TicketStatus { Todo, InProgress, Done }

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Project Attributes
  private String name;
  private int id;

  //Project Associations
  private UserRole userRole;
  private List<Invitation> invitations;
  private List<Ticket> t;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Project(String aName, int aId, UserRole aUserRole)
  {
    name = aName;
    id = aId;
    boolean didAddUserRole = setUserRole(aUserRole);
    if (!didAddUserRole)
    {
      throw new RuntimeException("Unable to create project due to userRole");
    }
    invitations = new ArrayList<Invitation>();
    t = new ArrayList<Ticket>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setId(int aId)
  {
    boolean wasSet = false;
    id = aId;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public int getId()
  {
    return id;
  }
  /* Code from template association_GetOne */
  public UserRole getUserRole()
  {
    return userRole;
  }
  /* Code from template association_GetMany */
  public Invitation getInvitation(int index)
  {
    Invitation aInvitation = invitations.get(index);
    return aInvitation;
  }

  public List<Invitation> getInvitations()
  {
    List<Invitation> newInvitations = Collections.unmodifiableList(invitations);
    return newInvitations;
  }

  public int numberOfInvitations()
  {
    int number = invitations.size();
    return number;
  }

  public boolean hasInvitations()
  {
    boolean has = invitations.size() > 0;
    return has;
  }

  public int indexOfInvitation(Invitation aInvitation)
  {
    int index = invitations.indexOf(aInvitation);
    return index;
  }
  /* Code from template association_GetMany */
  public Ticket getT(int index)
  {
    Ticket aT = t.get(index);
    return aT;
  }

  public List<Ticket> getT()
  {
    List<Ticket> newT = Collections.unmodifiableList(t);
    return newT;
  }

  public int numberOfT()
  {
    int number = t.size();
    return number;
  }

  public boolean hasT()
  {
    boolean has = t.size() > 0;
    return has;
  }

  public int indexOfT(Ticket aT)
  {
    int index = t.indexOf(aT);
    return index;
  }
  /* Code from template association_SetOneToMany */
  public boolean setUserRole(UserRole aUserRole)
  {
    boolean wasSet = false;
    if (aUserRole == null)
    {
      return wasSet;
    }

    UserRole existingUserRole = userRole;
    userRole = aUserRole;
    if (existingUserRole != null && !existingUserRole.equals(aUserRole))
    {
      existingUserRole.removeProject(this);
    }
    userRole.addProject(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfInvitations()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
//   public Invitation addInvitation(InvitationStatus aInvStatus, int aId, UserRole aUserRole)
//   {
//     return new Invitation(aInvStatus, aId, aUserRole, this);
//   }

  public boolean addInvitation(Invitation aInvitation)
  {
    boolean wasAdded = false;
    if (invitations.contains(aInvitation)) { return false; }
    Project existingProject = aInvitation.getProject();
    boolean isNewProject = existingProject != null && !this.equals(existingProject);
    if (isNewProject)
    {
      aInvitation.setProject(this);
    }
    else
    {
      invitations.add(aInvitation);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeInvitation(Invitation aInvitation)
  {
    boolean wasRemoved = false;
    //Unable to remove aInvitation, as it must always have a project
    if (!this.equals(aInvitation.getProject()))
    {
      invitations.remove(aInvitation);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addInvitationAt(Invitation aInvitation, int index)
  {  
    boolean wasAdded = false;
    if(addInvitation(aInvitation))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfInvitations()) { index = numberOfInvitations() - 1; }
      invitations.remove(aInvitation);
      invitations.add(index, aInvitation);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveInvitationAt(Invitation aInvitation, int index)
  {
    boolean wasAdded = false;
    if(invitations.contains(aInvitation))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfInvitations()) { index = numberOfInvitations() - 1; }
      invitations.remove(aInvitation);
      invitations.add(index, aInvitation);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addInvitationAt(aInvitation, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfT()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
//   public Ticket addT(TicketStatus aTicketStatus, String aDescription, Date aStartDate, Date aEndDate, UserRole aUserRole)
//   {
//     return new Ticket(aTicketStatus, aDescription, aStartDate, aEndDate, aUserRole, this);
//   }

  public boolean addT(Ticket aT)
  {
    boolean wasAdded = false;
    if (t.contains(aT)) { return false; }
    Project existingP = aT.getP();
    boolean isNewP = existingP != null && !this.equals(existingP);
    if (isNewP)
    {
      aT.setP(this);
    }
    else
    {
      t.add(aT);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeT(Ticket aT)
  {
    boolean wasRemoved = false;
    //Unable to remove aT, as it must always have a p
    if (!this.equals(aT.getP()))
    {
      t.remove(aT);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addTAt(Ticket aT, int index)
  {  
    boolean wasAdded = false;
    if(addT(aT))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfT()) { index = numberOfT() - 1; }
      t.remove(aT);
      t.add(index, aT);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveTAt(Ticket aT, int index)
  {
    boolean wasAdded = false;
    if(t.contains(aT))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfT()) { index = numberOfT() - 1; }
      t.remove(aT);
      t.add(index, aT);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addTAt(aT, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    UserRole placeholderUserRole = userRole;
    this.userRole = null;
    if(placeholderUserRole != null)
    {
      placeholderUserRole.removeProject(this);
    }
    for(int i=invitations.size(); i > 0; i--)
    {
      Invitation aInvitation = invitations.get(i - 1);
      aInvitation.delete();
    }
    while (t.size() > 0)
    {
      Ticket aT = t.get(t.size() - 1);
      aT.delete();
      t.remove(aT);
    }
    
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "id" + ":" + getId()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "userRole = "+(getUserRole()!=null?Integer.toHexString(System.identityHashCode(getUserRole())):"null");
  }
}