package ca.mcgill.ecse321.bugtracker.model;


// line 23 "../../../../BugTracker-Backend.ump"
public class Admin extends UserRole
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Admin(String aPassword, String aUserName, User aUser)
  {
    super(aPassword, aUserName, aUser);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public void delete()
  {
    super.delete();
  }

}