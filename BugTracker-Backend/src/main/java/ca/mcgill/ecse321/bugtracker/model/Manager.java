package ca.mcgill.ecse321.bugtracker.model;

import javax.persistence.Entity;

// line 12 "../../../../BugTracker-Backend.ump"
@Entity
public class Manager extends UserRole
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------
  public Manager(){
    
  }
  //------------------------
  // INTERFACE
  //------------------------

  public void delete()
  {
    super.delete();
  }

}