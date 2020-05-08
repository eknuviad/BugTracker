package ca.mcgill.ecse321.bugtracker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ca.mcgill.ecse321.bugtracker.dao.AccountRepository;
import ca.mcgill.ecse321.bugtracker.dao.ManagerRepository;
import ca.mcgill.ecse321.bugtracker.dao.ProjectRepository;
import ca.mcgill.ecse321.bugtracker.model.Account;
import ca.mcgill.ecse321.bugtracker.model.Manager;
import ca.mcgill.ecse321.bugtracker.model.Project;

@SpringBootTest
public class ProjectTest {
    @Autowired
    private ManagerRepository managerRepository;
    
    @Autowired
    private AccountRepository accRepository;

    @Autowired
    private ProjectRepository projectRepository;

    private String accName = "Doe";
    private String email = "johnDoe@gmail.com";
    private String desc = "Testing my john doe account";
    private int phoneNum = 123;
    private String pass = "test123";
    private String userName = "johnDoe";
    private String projName = "TestProject";

    
    @AfterEach
    public void clearDatabase(){  
        projectRepository.deleteAll();     
        managerRepository.deleteAll();
        accRepository.deleteAll();
        
    }

    /**
     * Project persistence test
     */

     @Test
     public void persistAndLoadProject(){
        Account acc = new Account (accName, email, desc, phoneNum);
        accRepository.save(acc);

        Manager manager = new Manager();
        manager.setUser(acc);
        manager.setPassword(pass);
        manager.setUserName(userName);
        
        managerRepository.save(manager); 

        Project proj = new Project();
        proj.setName(projName);
        proj.setUserRole(manager);

        projectRepository.save(proj);

        proj = null;
        proj = projectRepository.findProjectByName(projName);
        assertNotNull(proj);
        assertEquals(projName, proj.getName());
        assertEquals (userName, proj.getUserRole().getUserName());
        assertEquals (email, proj.getUserRole().getUser().getEmail());
    

     }
}