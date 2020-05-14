package ca.mcgill.ecse321.bugtracker.controller;

import java.rmi.AccessException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.bugtracker.dao.AccountRepository;
import ca.mcgill.ecse321.bugtracker.dto.AccountDTO;
import ca.mcgill.ecse321.bugtracker.dto.UserRoleDTO;
import ca.mcgill.ecse321.bugtracker.model.Account;
import ca.mcgill.ecse321.bugtracker.model.Admin;
import ca.mcgill.ecse321.bugtracker.model.Manager;
import ca.mcgill.ecse321.bugtracker.model.UserRole;
import ca.mcgill.ecse321.bugtracker.service.AccountService;
import ca.mcgill.ecse321.bugtracker.service.UserRoleService;

@CrossOrigin(origins = "*")
@RestController
public class AccountRestController {

    @Autowired 
    private AccountService accountService;

    @Autowired
    private UserRoleService urService;

    
    @PostMapping({"/create/account", "/create/account/"})
    public AccountDTO createAccount(@RequestParam("accName") String name,
            @RequestParam("accEmail") String email,
            @RequestParam("description") String description,
            @RequestParam("phoneNum") int phoneNumber){
        
        Account account = accountService.createAccount(name, email, description, phoneNumber);
        List<UserRole> userRoles = urService.getAllUserRolesByAccount(account);
        AccountDTO accountDto = convertToDTO(account, userRoles);
        return accountDto;
    }

    @PostMapping({"/addrole/manager", "/addrole/manager/"})
    public AccountDTO createManagerRole(@RequestParam("userName") String userName,
            @RequestParam("password") String password,
            @RequestParam("email") String email) {
        
        Account account = accountService.getAccountByEmail(email);
        if (account == null){
            throw new IllegalArgumentException("Account dosen't exists.");
        }
        Manager manager = urService.createManagerRoleByAccount(password, userName, account);
        List<UserRole> roles = urService.getAllUserRolesByAccount(account);
        AccountDTO accountDto = convertToDTO(account, roles);
        return accountDto;
        
    }






    private AccountDTO convertToDTO(Account account, List<UserRole> userrole ) {
        if (account == null) {
            throw new IllegalArgumentException("There is no account.");
        }
        
        List<UserRoleDTO> urDTO = new ArrayList<>();
        for(UserRole u : userrole){
            urDTO.add(convertToDTO(u));
        }
        AccountDTO accDTO = new AccountDTO(account.getName(),account.getEmail(), account.getDescription(), account.getPhoneNumber(),urDTO);
        return accDTO;
    
    }

    private UserRoleDTO convertToDTO(UserRole ur){
        if (ur == null) {
            throw new IllegalArgumentException("There is no userrole.");
        }
        UserRoleDTO urDTO;
        String username;
        if(ur instanceof Manager){
            username = "Manager-" + ur.getUserName();
        }else if (ur instanceof Admin){
             username = "Admin-" + ur.getUserName();
        }else{
            username = "Developer-" + ur.getUserName(); 
        }
        urDTO = new UserRoleDTO (username, ur.getUser().getEmail());
        return urDTO;
    }
}