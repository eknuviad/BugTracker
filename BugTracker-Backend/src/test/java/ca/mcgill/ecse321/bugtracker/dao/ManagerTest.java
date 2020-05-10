package ca.mcgill.ecse321.bugtracker.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import ca.mcgill.ecse321.bugtracker.model.Manager;
import ca.mcgill.ecse321.bugtracker.model.Account;

@SpringBootTest //important tag for autowiring crud repository as a spring test
public class ManagerTest {
    @Autowired
    private ManagerRepository managerRepository;
    
    @Autowired
    private AccountRepository accRepository;

    private String accName = "Doe";
    private String email = "johnDoe@gmail.com";
    private String desc = "Testing my john doe account";
    private int phoneNum = 123;
    private String pass = "test123";
    private String userName = "johnDoe";

    @AfterEach
    public void clearDatabase(){       
        managerRepository.deleteAll();
        accRepository.deleteAll();
    }

    /**
     * Manager persistence test
     */

     @Test
     public void persistAndLoadManager(){
        Account acc = new Account (accName, email, desc, phoneNum);
        accRepository.save(acc);

        Manager manager = new Manager();
        manager.setUser(acc);
        manager.setPassword(pass);
        manager.setUserName(userName);
        
        managerRepository.save(manager); 

        manager = null;

        manager = managerRepository.findManagerByUserName(userName);
        assertNotNull(manager);
        assertEquals(pass, manager.getPassword());
        assertEquals (userName, manager.getUserName());
        assertEquals (email, manager.getUser().getEmail());
    

     }
    
}