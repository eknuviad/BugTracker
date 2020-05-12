package ca.mcgill.ecse321.bugtracker.service;

import javax.validation.constraints.Null;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.bugtracker.dao.AccountRepository;
import ca.mcgill.ecse321.bugtracker.model.Account;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;
    
    @Transactional
    public Account createAccount(String name, String email, String description, int phoneNumber) throws RuntimeException{
        Account temp = accountRepository.findAccountByEmail(email);
        if (temp != null){
            throw new IllegalArgumentException(
                    "There is already an account with that email address: " + temp.getEmail());
        }
        Account account = new Account(name, email, description, phoneNumber);
        accountRepository.save(account);
        return account;
    }

    @Transactional
    public Account getAccountByEmail(String email){
        Account account = accountRepository.findAccountByEmail(email);
        return account;
    }

    @Transactional
    public boolean emailExist(String email){
        Account account = accountRepository.findAccountByEmail(email);
        if (account != null){
            return true;
        }
        return false;
    }

    @Transactional
    public void deleteAccount(String email){
         Account account = accountRepository.findAccountByEmail(email);
         if (account == null){
             return;
         }
        accountRepository.delete(account);
    }
    
}