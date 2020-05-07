package ca.mcgill.ecse321.bugtracker.model;

import javax.persistence.Entity;

// line 28 "../../../../BugTracker-Backend.ump"
@Entity
public class Developer extends UserRole
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Developer(String aPassword, String aUserName, Account aAcc)
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