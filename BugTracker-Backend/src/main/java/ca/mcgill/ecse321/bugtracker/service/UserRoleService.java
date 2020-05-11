package ca.mcgill.ecse321.bugtracker.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.bugtracker.dao.AccountRepository;
import ca.mcgill.ecse321.bugtracker.dao.AdminRepository;
import ca.mcgill.ecse321.bugtracker.dao.DeveloperRepository;
import ca.mcgill.ecse321.bugtracker.dao.ManagerRepository;
import ca.mcgill.ecse321.bugtracker.dao.UserRoleRepository;
import ca.mcgill.ecse321.bugtracker.model.Account;
import ca.mcgill.ecse321.bugtracker.model.Admin;
import ca.mcgill.ecse321.bugtracker.model.Manager;

/**
 * @author eknuviad
 */
@Service
public class UserRoleService {
    
    @Autowired
    AccountRepository accRepository;
    @Autowired
    UserRoleRepository userRoleRepository;
    @Autowired
    ManagerRepository managerRepository;
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    DeveloperRepository devRepository;


    @Transactional
    public Manager createManagerRoleByAccount(String password, String usrName, Account acc){
        String error = "";
        //null checks
        if(acc == null){
            error = error + "The account to create a Manager cannot be empty.";
        }
        if(password == null || password.trim().length() == 0){
            error = error + "The Manager password cannot be empty or have spaces.";
        }
        if(usrName == null || usrName.trim().length() == 0){
            error = error + "The Manager username cannot be empty or have spaces.";
        }
        //check if username already exists
        if(userRoleRepository.existsByUsername(usrName)){ 
            error = error + "This Manager username is already taken.";
        }
        if(error.length() > 0){
            throw new IllegalArgumentException(error);
        }
        String email = acc.getEmail();
        Account foundAcc = accRepository.findAccountByEmail(email);
        if(foundAcc == null){
            throw new NullPointerException("No such account exists to create Manager role.");
        }
        Manager manager = new Manager();
        manager.setPassword(password);
        manager.setUserName(usrName);
        manager.setUser(acc);

        managerRepository.save(manager);

        return manager;
    }

    @Transactional
    public Admin createAdminRoleByAccount(String password, String usrName, Account acc){
        String error = "";
        //null checks
        if(acc == null){
            error = error + "The account to create an Admin cannot be empty.";
        }
        if(password == null || password.trim().length() == 0){
            error = error + "The Admin password cannot be empty or have spaces.";
        }
        if(usrName == null || usrName.trim().length() == 0){
            error = error + "The Admin username cannot be empty or have spaces.";
        }
        //check if username already exists
        if(userRoleRepository.existsByUsername(usrName)){ 
            error = error + "This Admin username is already taken.";
        }
        if(error.length() > 0){
            throw new IllegalArgumentException(error);
        }
        String email = acc.getEmail();
        Account foundAcc = accRepository.findAccountByEmail(email);
        if(foundAcc == null){
            throw new NullPointerException("No such account exists to create Admin role.");
        }
        Admin admin = new Admin();
        admin.setPassword(password);
        admin.setUserName(usrName);
        admin.setUser(acc);

        adminRepository.save(admin);

        return admin;
    }

    /**
     *
     * @param iterable
     * @param <T>
     * @return
     */
    private <T> List<T> toList(Iterable<T> iterable) {
        List<T> resultList = new ArrayList<T>();
        for (T t : iterable) {
            resultList.add(t);
        }
        return resultList;
    }
}