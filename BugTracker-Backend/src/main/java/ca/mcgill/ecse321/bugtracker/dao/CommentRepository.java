package ca.mcgill.ecse321.bugtracker.dao;
import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.bugtracker.model.Comment;

public interface CommentRepository extends CrudRepository<Comment, String>{

	//Comment findAccountByName(String name);

}