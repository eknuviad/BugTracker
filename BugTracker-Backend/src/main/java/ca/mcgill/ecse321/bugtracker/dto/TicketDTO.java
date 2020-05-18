
package ca.mcgill.ecse321.bugtracker.dto;

import java.sql.Date;

import ca.mcgill.ecse321.bugtracker.model.Project.TicketStatus;

public class TicketDTO {

    private TicketStatus ticketStatus;
    private String description;
    private Date startDate;
    private Date endDate;
  
    //Ticket Associations
    // private UserRole userRole;
    // private Project p;
    private int id;

    public TicketDTO (TicketStatus status, String description, Date startDate, Date endDate, int id){
        this.ticketStatus = status;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.id = id;
    }

    public TicketStatus getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(TicketStatus ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
}