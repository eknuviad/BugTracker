package ca.mcgill.ecse321.bugtracker.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

// line 60 "../../../../BugTracker-Backend.ump"
@Entity
public class Ticket
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum TicketStatus { Todo, InProgress, Done }

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Ticket Attributes
  private TicketStatus ticketStatus;
  private String description;
  private Date startDate;
  private Date endDate;

  //Ticket Associations
  private UserRole userRole;
  private Project p;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Ticket(TicketStatus aTicketStatus, String aDescription, Date aStartDate, Date aEndDate, UserRole aUserRole, Project aP)
  {
    ticketStatus = aTicketStatus;
    description = aDescription;
    startDate = aStartDate;
    endDate = aEndDate;
    this.userRole = aUserRole;
    this.p = aP;
  }

  public Ticket() {
}

//------------------------
  // INTERFACE
  //------------------------
  private int id;

    public void setId(int value) {
        this.id = value;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return this.id;
    }

  public boolean setTicketStatus(TicketStatus aTicketStatus)
  {
    boolean wasSet = false;
    ticketStatus = aTicketStatus;
    wasSet = true;
    return wasSet;
  }

  public boolean setDescription(String aDescription)
  {
    boolean wasSet = false;
    description = aDescription;
    wasSet = true;
    return wasSet;
  }

  public boolean setStartDate(Date aStartDate)
  {
    boolean wasSet = false;
    startDate = aStartDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setEndDate(Date aEndDate)
  {
    boolean wasSet = false;
    endDate = aEndDate;
    wasSet = true;
    return wasSet;
  }

  public TicketStatus getTicketStatus()
  {
    return ticketStatus;
  }

  public String getDescription()
  {
    return description;
  }

  public Date getStartDate()
  {
    return startDate;
  }

  public Date getEndDate()
  {
    return endDate;
  }
  /* Code from template association_GetOne */
  @ManyToOne(optional = false)
  public UserRole getUserRole()
  {
    return userRole;
  }
  /* Code from template association_GetOne */
  @ManyToOne(optional = false)
  public Project getP()
  {
    return p;
  }
  /* Code from template association_SetOneToMany */
  public void setUserRole(UserRole aUserRole)
  {
    this.userRole = aUserRole;
  }
  /* Code from template association_SetOneToMany */
  public void setP(Project aP)
  {
    this.p = aP;
  }

  public void delete()
  {
    UserRole placeholderUserRole = userRole;
    this.userRole = null;
    if(placeholderUserRole != null)
    {
      placeholderUserRole.removeTicket(this);
    }
    Project placeholderP = p;
    this.p = null;
    if(placeholderP != null)
    {
      placeholderP.removeT(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "description" + ":" + getDescription()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "ticketStatus" + "=" + (getTicketStatus() != null ? !getTicketStatus().equals(this)  ? getTicketStatus().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "startDate" + "=" + (getStartDate() != null ? !getStartDate().equals(this)  ? getStartDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "endDate" + "=" + (getEndDate() != null ? !getEndDate().equals(this)  ? getEndDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "userRole = "+(getUserRole()!=null?Integer.toHexString(System.identityHashCode(getUserRole())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "p = "+(getP()!=null?Integer.toHexString(System.identityHashCode(getP())):"null");
  }
}