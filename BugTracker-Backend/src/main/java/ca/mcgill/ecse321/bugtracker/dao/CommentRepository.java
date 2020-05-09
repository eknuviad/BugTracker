package ca.mcgill.ecse321.bugtracker.dao;

import java.sql.Time;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.bugtracker.model.Comment;
import ca.mcgill.ecse321.bugtracker.model.Ticket;

public interface CommentRepository extends CrudRepository<Comment, String>{

	Comment findCommentByTimeStampAndTicket(Time time, Ticket ticket);

}