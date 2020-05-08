package ca.mcgill.ecse321.bugtracker.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.bugtracker.dao.InvitationRepository;
import ca.mcgill.ecse321.bugtracker.model.Account;
import ca.mcgill.ecse321.bugtracker.model.Invitation;
import ca.mcgill.ecse321.bugtracker.model.Manager;
import ca.mcgill.ecse321.bugtracker.model.Project;
import ca.mcgill.ecse321.bugtracker.model.Invitation.InvitationStatus;

import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class InvitationTest {

    @Autowired
    private InvitationRepository invitationRepository;

    private String accName = "He";
    private String email = "ziruiHe@gmail.com";
    private String desc = "Testing my account";
    private int phoneNum = 12345;
    private String pass = "test12345";
    private String userName = "ziruiHe";
    
    @AfterEach
    public void clearDatabase() {
        invitationRepository.deleteAll();
    }

    @Test
    public void testInvitation(){
        Account acc = new Account (accName, email, desc, phoneNum);

        Manager manager = new Manager();
        manager.setUser(acc);
        manager.setPassword(pass);
        manager.setUserName(userName);

        Project project = new Project();
        project.setId(357);
        project.setUserRole(manager);

        Integer id = 123;
        Invitation invitation = new Invitation(InvitationStatus.NewInvite, id, manager, project);
        invitationRepository.save(invitation);
        invitation = null;
        invitation = invitationRepository.findInvitationById(id);
        assertNotNull(invitation);
        assertEquals(id, invitation.getId());
    }    
    
}