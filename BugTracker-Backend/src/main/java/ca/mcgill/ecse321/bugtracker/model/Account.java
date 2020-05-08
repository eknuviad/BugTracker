package ca.mcgill.ecse321.bugtracker.model;


import javax.persistence.Entity;
import javax.persistence.Id;

// line 3 "../../../../BugTracker-Backend.ump"
@Entity
public class Account {

  // ------------------------
  // MEMBER VARIABLES
  // ------------------------

  // User Attributes
  private String name;
  private String email;
  private String description;
  private int phoneNumber;

  // ------------------------
  // CONSTRUCTOR
  // ------------------------
  public Account(){
    
  }

  public Account(String aName, String aEmail, String aDescription, int aPhoneNumber) {
    name = aName;
    email = aEmail;
    description = aDescription;
    phoneNumber = aPhoneNumber;
  }

  // ------------------------
  // INTERFACE
  // ------------------------

  public boolean setName(String aName) {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setEmail(String aEmail) {
    boolean wasSet = false;
    email = aEmail;
    wasSet = true;
    return wasSet;
  }

  public boolean setDescription(String aDescription) {
    boolean wasSet = false;
    description = aDescription;
    wasSet = true;
    return wasSet;
  }

  public boolean setPhoneNumber(int aPhoneNumber) {
    boolean wasSet = false;
    phoneNumber = aPhoneNumber;
    wasSet = true;
    return wasSet;
  }

  public String getName() {
    return name;
  }

  @Id
  public String getEmail() {
    return email;
  }

  public String getDescription() {
    return description;
  }

  public int getPhoneNumber() {
    return phoneNumber;
  }

  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfUserRoles() {
    return 0;
  }

  /* Code from template association_MaximumNumberOfMethod */
  public static int maximumNumberOfUserRoles() {
    return 3;
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "email" + ":" + getEmail()+ "," +
            "description" + ":" + getDescription()+ "," +
            "phoneNumber" + ":" + getPhoneNumber()+ "]";
  }
}