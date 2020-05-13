package ca.mcgill.ecse321.bugtracker.service;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.bugtracker.dao.CommentRepository;
import ca.mcgill.ecse321.bugtracker.model.Comment;
import ca.mcgill.ecse321.bugtracker.model.Ticket;
import ca.mcgill.ecse321.bugtracker.model.UserRole;

public class CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Transactional
    public Comment createComment(Time aTimeStamp, String aMessage, UserRole aUserRole, Ticket aTicket) throws RuntimeException{
        Comment temp = commentRepository.findCommentByTimeStampAndTicket(aTimeStamp, aTicket);
        if (temp != null){
            throw new IllegalArgumentException(
                "There is already an comment with that timeStmap: " + temp.getTimeStamp());
        }
        Comment comment = new Comment(aTimeStamp, aMessage, aUserRole, aTicket);
        commentRepository.save(comment);
        return comment;
    }

    @Transactional
    public boolean updateComment(Comment comment, String description){
        if (comment == null){
            return false;
        }
        comment.setMessage(description);
        commentRepository.save(comment);
        return true;
    }

    @Transactional
    public List<Comment> getAllCommentByUserRole(UserRole role){
        return toList(commentRepository.findAllByUserRole(role));
    }

    @Transactional
    public List<Comment> getAllComments(){
        return toList(commentRepository.findAll());
    }

    @Transactional
    public Comment getCommentById(int id){
        Comment comment = commentRepository.findCommentById(id);
        return comment;
    }

    @Transactional
    public boolean deleteCommentById(int id){
        Comment comment = commentRepository.findCommentById(id);
        if (comment == null){
            return false;
        }
        commentRepository.delete(comment);
        return true;
    }

    private <T> List<T> toList(Iterable<T> iterable) {
        List<T> resultList = new ArrayList<T>();
        for (T t : iterable) {
            resultList.add(t);
        }
        return resultList;
    }
}