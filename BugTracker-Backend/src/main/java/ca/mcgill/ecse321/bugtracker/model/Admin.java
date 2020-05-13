package ca.mcgill.ecse321.bugtracker.model;

import javax.persistence.Entity;

// line 23 "../../../../BugTracker-Backend.ump"
@Entity
public class Admin extends UserRole
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Admin(){
    
  }

  public Admin(String aPassword, String aUserName, Account aAcc)
  {
    super(aPassword, aUserName, aAcc);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public void delete()
  {
    super.delete();
  }

}