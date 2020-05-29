
package ca.mcgill.ecse321.bugtracker.dto;

import java.sql.Time;

import ca.mcgill.ecse321.bugtracker.model.Ticket;
import ca.mcgill.ecse321.bugtracker.model.UserRole;

public class CommentDTO {

    private Time timeStamp;
    private String message;
  
    //Comment Associations
    private UserRole userRole;
    private Ticket ticket;

    private int id;

    public CommentDTO (Time time, String message, UserRole role, Ticket ticket, int id){
        this.timeStamp = time;
        this.message = message;
        this.userRole = role;
        this.ticket = ticket;
        this.id = id;
    }

    public Time getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Time timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
}