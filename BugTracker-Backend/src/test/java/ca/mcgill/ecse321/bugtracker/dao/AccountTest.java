package ca.mcgill.ecse321.bugtracker.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.bugtracker.dao.AccountRepository;
import ca.mcgill.ecse321.bugtracker.model.Account;

import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class AccountTest {

    @Autowired
    private AccountRepository accountRepository;
    
    @AfterEach
    public void clearDatabase() {
        accountRepository.deleteAll();
    }
    
    @Test
    public void testAccount(){
        String email = "TestEmail";
        Account account = new Account("TestName", email, "TestDescription", 123456);
        accountRepository.save(account);
        account = null;
        account = accountRepository.findAccountByEmail(email);
        assertNotNull(account);
        assertEquals(email, account.getEmail());
    }
}

