package ca.mcgill.ecse321.bugtracker.model;


// line 28 "../../../../BugTracker-Backend.ump"
public class Developer extends UserRole
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Developer(String aPassword, String aUserName, User aUser)
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