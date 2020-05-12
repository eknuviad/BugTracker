package ca.mcgill.ecse321.bugtracker.dao;

import java.sql.Time;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.bugtracker.model.Comment;
import ca.mcgill.ecse321.bugtracker.model.Ticket;
import ca.mcgill.ecse321.bugtracker.model.UserRole;

public interface CommentRepository extends CrudRepository<Comment, String>{

	Comment findCommentByTimeStampAndTicket(Time time, Ticket ticket);

	List<Comment> findAllByUserRole(UserRole role);

	Comment findCommentById(int id);

}