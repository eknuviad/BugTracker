package ca.mcgill.ecse321.bugtracker.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.bugtracker.model.Account;
import ca.mcgill.ecse321.bugtracker.model.Developer;
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

    @Autowired
    private ManagerRepository managerRepository;

    @Autowired
    private DeveloperRepository developerRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private UserRoleRepository userRepository;

    private String accName = "He";
    private String email = "ziruiHe@gmail.com";
    private String desc = "Testing my account";
    private int phoneNum = 12345;
    private String pass = "test12345";
    private String userName = "ziruiHe";
    
    @AfterEach
    public void clearDatabase() {
        invitationRepository.deleteAll();
        userRepository.deleteAll();
        accountRepository.deleteAll();
        projectRepository.deleteAll();
    }

    @Test
    public void testInvitation(){
        Account acc1 = new Account (accName, email, desc, phoneNum);
        accountRepository.save(acc1);

        Account acc2 = new Account ("accName", "email", "desc", 67890);
        accountRepository.save(acc2);

        Manager manager = new Manager();
        manager.setUser(acc1);
        manager.setPassword(pass);
        manager.setUserName(userName);
        managerRepository.save(manager);

        Developer developer = new Developer();
        developer.setUser(acc2);
        developer.setPassword("pass");
        developer.setUserName("Quintus");
        developerRepository.save(developer);

        Project project = new Project();
        project.setUserRole(manager);
        projectRepository.save(project);

        Invitation invitation = new Invitation(InvitationStatus.NewInvite, manager, developer, project);
        invitationRepository.save(invitation);
        invitation = null;
        //invitation = invitationRepository.findInvitationById(id);
        invitation = invitationRepository.findByProjectAndReceiver(project, developer);
        assertNotNull(invitation);
        assertEquals(project.getName(), invitation.getProject().getName());
        assertEquals(manager.getUser().getName(), invitation.getSender().getUser().getName());
        assertEquals(developer.getUser().getName(), invitation.getReceiver().getUser().getName());
        assertEquals(InvitationStatus.NewInvite, invitation.getInvStatus());
    }    
    
}