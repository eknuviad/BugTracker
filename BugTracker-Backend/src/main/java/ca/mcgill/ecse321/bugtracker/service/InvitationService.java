package ca.mcgill.ecse321.bugtracker.service;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.bugtracker.dao.CommentRepository;
import ca.mcgill.ecse321.bugtracker.dao.InvitationRepository;
import ca.mcgill.ecse321.bugtracker.model.Comment;
import ca.mcgill.ecse321.bugtracker.model.Developer;
import ca.mcgill.ecse321.bugtracker.model.Invitation;
import ca.mcgill.ecse321.bugtracker.model.Project;
import ca.mcgill.ecse321.bugtracker.model.Ticket;
import ca.mcgill.ecse321.bugtracker.model.UserRole;
import ca.mcgill.ecse321.bugtracker.model.Invitation.InvitationStatus;

@Service
public class InvitationService {

    @Autowired
    InvitationRepository invitationRepository;

    @Transactional
    public Invitation createInvitation(InvitationStatus status, UserRole sender, UserRole receiver, Project project) throws RuntimeException{
        Invitation temp = invitationRepository.findByProjectAndReceiver(project, receiver);
        if (temp != null){
            throw new IllegalArgumentException(
                "There is already an invitation for that userRole in: " + temp.getProject().getName());
        }

        boolean isDeveloper = sender instanceof Developer;
        if (isDeveloper){
            throw new IllegalArgumentException(
                "A developer cannot send an invitation.");
        }

        Invitation invitation = new Invitation(status, sender, receiver, project);
        invitationRepository.save(invitation);
        return invitation;
    }

    @Transactional
    public Invitation getInvitationByProjectNameAndReceiver(Project project, UserRole receiver){
        Invitation invitation = invitationRepository.findByProjectAndReceiver(project, receiver);
        return invitation;
    }

    @Transactional
    public List<Invitation> getInvitationByReceiver(UserRole receiver){
        return toList(invitationRepository.findAllByReceiver(receiver));
    }

    @Transactional
    public List<Invitation> getAllInvitationByProject(Project project){
        return toList(invitationRepository.findAllByProject(project));
    }

    @Transactional
    public boolean updateInvitationStatus(Invitation invitation, InvitationStatus status){
        if (invitation == null){
            return false;
        }
        invitation.setInvStatus(status);
        invitationRepository.save(invitation);
        return true;
    }

    @Transactional 
    public boolean deleteInvitationById(int id){
        Invitation invitation = invitationRepository.findById(id);
        if (invitation == null){
            return false;
        }
        invitationRepository.delete(invitation);
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