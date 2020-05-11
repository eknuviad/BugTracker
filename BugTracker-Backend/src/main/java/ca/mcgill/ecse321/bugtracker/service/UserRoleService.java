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
import ca.mcgill.ecse321.bugtracker.model.Developer;
import ca.mcgill.ecse321.bugtracker.model.Manager;
import ca.mcgill.ecse321.bugtracker.model.UserRole;

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
    public Manager createManagerRoleByAccount(String password, String usrName, Account acc) {
        String error = "";
        // null checks
        if (acc == null) {
            error = error + "The account to create a Manager cannot be empty.";
        }
        if (password == null || password.trim().length() == 0) {
            error = error + "The Manager password cannot be empty or have spaces.";
        }
        if (usrName == null || usrName.trim().length() == 0) {
            error = error + "The Manager username cannot be empty or have spaces.";
        }
        // check if username already exists
        String email = acc.getEmail();
        if (managerRepository.existsByUserName(usrName)) {
            if (managerRepository.findManagerByUserName(usrName).getUser().getEmail().equals(email)) {
                throw new IllegalArgumentException("A manager profile already exists for this account.");
            } else {
                error = error + "This Manager username is already taken.";
            }
        }
        if (error.length() > 0) {
            throw new IllegalArgumentException(error);
        }
        Account foundAcc = accRepository.findAccountByEmail(email);
        if (foundAcc == null) {
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
    public Admin createAdminRoleByAccount(String password, String usrName, Account acc) {
        String error = "";
        // null checks
        if (acc == null) {
            error = error + "The account to create an Admin cannot be empty.";
        }
        if (password == null || password.trim().length() == 0) {
            error = error + "The Admin password cannot be empty or have spaces.";
        }
        if (usrName == null || usrName.trim().length() == 0) {
            error = error + "The Admin username cannot be empty or have spaces.";
        }
        // check if username already exists
        String email = acc.getEmail();
        if (adminRepository.existsByUserName(usrName)) {
            if (adminRepository.findAdminByUserName(usrName).getUser().getEmail().equals(email)) {
                throw new IllegalArgumentException("An Admin profile already exists for this account.");
            } else {
                error = error + "This Admin username is already taken.";
            }
        }
        if (error.length() > 0) {
            throw new IllegalArgumentException(error);
        }
        Account foundAcc = accRepository.findAccountByEmail(email);
        if (foundAcc == null) {
            throw new NullPointerException("No such account exists to create Admin role.");
        }
        Admin admin = new Admin();
        admin.setPassword(password);
        admin.setUserName(usrName);
        admin.setUser(acc);

        adminRepository.save(admin);

        return admin;
    }

    @Transactional
    public Developer createDeveloperRoleByAccount(String password, String usrName, Account acc) {
        String error = "";
        // null checks
        if (acc == null) {
            error = error + "The account to create a Developer cannot be empty.";
        }
        if (password == null || password.trim().length() == 0) {
            error = error + "The Developer password cannot be empty or have spaces.";
        }
        if (usrName == null || usrName.trim().length() == 0) {
            error = error + "The Developer username cannot be empty or have spaces.";
        }
        // check if username already exists
        String email = acc.getEmail();
        if (devRepository.existsByUserName(usrName)) {
            if (devRepository.findDeveloperByUserName(usrName).getUser().getEmail().equals(email)) {
                throw new IllegalArgumentException("A Developer profile already exists for this account.");
            } else {
                error = error + "This Developer username is already taken.";
            }
        }
        if (error.length() > 0) {
            throw new IllegalArgumentException(error);
        }
        Account foundAcc = accRepository.findAccountByEmail(email);
        if (foundAcc == null) {
            throw new NullPointerException("No such account exists to create Developer role.");
        }
        Developer dev = new Developer();
        dev.setPassword(password);
        dev.setUserName(usrName);
        dev.setUser(acc);

        devRepository.save(dev);

        return dev;
    }

    @Transactional
    public List<UserRole> getAllUserRolesByAccount(Account acc) {
        Account foundAcc = accRepository.findAccountByEmail(acc.getEmail());
        if (foundAcc == null) {
            throw new NullPointerException("No such account exists.");
        }
        List<UserRole> urList = new ArrayList<>();
        if (managerRepository.findByUserEmail(acc.getEmail()) != null) {
            urList.add(managerRepository.findByUserEmail(acc.getEmail()));
        }
        if (adminRepository.findByUserEmail(acc.getEmail()) != null) {
            urList.add(adminRepository.findByUserEmail(acc.getEmail()));
        }
        if (devRepository.findByUserEmail(acc.getEmail()) != null) {
            urList.add(devRepository.findByUserEmail(acc.getEmail()));
        }
        return toList(urList);
    }

    @Transactional
    public List<Manager> getAllManagers() {
        return toList(managerRepository.findAll());
    }

    @Transactional
    public List<Admin> getAllAdmins() {
        return toList(adminRepository.findAll());
    }

    @Transactional
    public List<Developer> getAllDevelopers() {
        return toList(devRepository.findAll());
    }

    @Transactional
    public UserRole getUserRoleByPasswordAndUserName(String password, String userName) {
        String error = "";
        // null checks
        if (password == null || password.trim().length() == 0) {
            error = error + "The password cannot be empty or have spaces.";
        }
        if (userName == null || userName.trim().length() == 0) {
            error = error + "The user name cannot be empty or have spaces.";
        }
        if (error.length() > 0) {
            throw new IllegalArgumentException(error);
        }
        UserRole ur = userRoleRepository.findByPasswordAndUserName(password, userName);
        if (ur == null) {
            throw new NullPointerException("No user role exists.");
        }

        return ur;
    }

    @Transactional
    public void updateUserRole(String newpassword, String olduserName, UserRole ur){
        String error = "";
        // null checks
        if (ur == null) {
            error = error + "The user role cannot be empty.";
        }
        if (olduserName == null || olduserName.trim().length() == 0) {
            error = error + "The new username cannot be empty or have spaces.";
            }
        if (newpassword == null || newpassword.trim().length() == 0) {
        error = error + "The new password cannot be empty or have spaces.";
        }
        if(userRoleRepository.existsById(olduserName)){
            ur.setPassword(newpassword);
            userRoleRepository.save(ur);
        }else{
            error = error + "This user role does not exist.";
        }
        if (error.length() > 0) {
            throw new IllegalArgumentException(error);
        }
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