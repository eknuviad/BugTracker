
package ca.mcgill.ecse321.bugtracker.dto;

import java.sql.Time;

import ca.mcgill.ecse321.bugtracker.model.Ticket;
import ca.mcgill.ecse321.bugtracker.model.UserRole;

public class CommentDTO {

    private Time timeStamp;
    private String message;
  
    //Comment Associations
    private UserRoleDTO userRole;
    private TicketDTO ticket;

    private int id;

    public CommentDTO (Time time, String message, UserRoleDTO urDto, TicketDTO tDto, int id){
        this.timeStamp = time;
        this.message = message;
        this.userRole = urDto;
        this.ticket = tDto;
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

    public UserRoleDTO getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRoleDTO userRole) {
        this.userRole = userRole;
    }

    public TicketDTO getTicket() {
        return ticket;
    }

    public void setTicket(TicketDTO ticket) {
        this.ticket = ticket;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    } 
    
}