package ca.mcgill.ecse321.bugtracker.model;


// line 12 "../../../../BugTracker-Backend.ump"
public class Manager extends UserRole
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Manager(String aPassword, String aUserName, User aUser)
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