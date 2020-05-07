package ca.mcgill.ecse321.bugtracker.model;

import java.sql.Time;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

// line 50 "../../../../BugTracker-Backend.ump"
@Entity
public class Comment
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Comment Attributes
  private Time timeStamp;
  private String message;

  //Comment Associations
  private UserRole userRole;
  private Ticket ticket;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Comment(Time aTimeStamp, String aMessage, UserRole aUserRole, Ticket aTicket)
  {
    timeStamp = aTimeStamp;
    message = aMessage;
    if (!setUserRole(aUserRole))
    {
      throw new RuntimeException("Unable to create Comment due to aUserRole");
    }
    if (!setTicket(aTicket))
    {
      throw new RuntimeException("Unable to create Comment due to aTicket");
    }
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

  public boolean setTimeStamp(Time aTimeStamp)
  {
    boolean wasSet = false;
    timeStamp = aTimeStamp;
    wasSet = true;
    return wasSet;
  }

  public boolean setMessage(String aMessage)
  {
    boolean wasSet = false;
    message = aMessage;
    wasSet = true;
    return wasSet;
  }

  public Time getTimeStamp()
  {
    return timeStamp;
  }

  public String getMessage()
  {
    return message;
  }
  /* Code from template association_GetOne */
  @ManyToOne
  public UserRole getUserRole()
  {
    return userRole;
  }
  /* Code from template association_GetOne */
  @ManyToOne
  public Ticket getTicket()
  {
    return ticket;
  }
  /* Code from template association_SetUnidirectionalOne */
  public boolean setUserRole(UserRole aNewUserRole)
  {
    boolean wasSet = false;
    if (aNewUserRole != null)
    {
      userRole = aNewUserRole;
      wasSet = true;
    }
    return wasSet;
  }
  /* Code from template association_SetUnidirectionalOne */
  public boolean setTicket(Ticket aNewTicket)
  {
    boolean wasSet = false;
    if (aNewTicket != null)
    {
      ticket = aNewTicket;
      wasSet = true;
    }
    return wasSet;
  }

  public void delete()
  {
    userRole = null;
    ticket = null;
  }


  public String toString()
  {
    return super.toString() + "["+
            "message" + ":" + getMessage()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "timeStamp" + "=" + (getTimeStamp() != null ? !getTimeStamp().equals(this)  ? getTimeStamp().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "userRole = "+(getUserRole()!=null?Integer.toHexString(System.identityHashCode(getUserRole())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "ticket = "+(getTicket()!=null?Integer.toHexString(System.identityHashCode(getTicket())):"null");
  }
}