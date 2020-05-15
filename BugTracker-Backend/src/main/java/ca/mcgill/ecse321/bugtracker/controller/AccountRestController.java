package ca.mcgill.ecse321.bugtracker.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    private AccountService aService;

    @Autowired
    private UserRoleService urService;


    @PostMapping(value = {"/addrole/developer/", "/addrole/developer"})
    public AccountDTO createDeveloperByAccount(@RequestParam("username") final String username, @RequestParam("password") final String password, @RequestParam("email") final String email)throws IllegalArgumentException {
        final Account ac = aService.getAccountByEmail(email);
        final String devName = "Developer-" + username;
        urService.createDeveloperRoleByAccount(password, devName, ac);
        final List<UserRole> ur = urService.getAllUserRolesByAccount(ac);
        return convertToDTO(ac, ur);
    }

    @GetMapping(value = {"/account/email/", "/account/email"})
    public AccountDTO getAccountByEmail(@RequestParam("email") final String email)throws IllegalArgumentException {
        final Account ac = aService.getAccountByEmail(email);
        final List<UserRole> ur = urService.getAllUserRolesByAccount(ac);
        return convertToDTO(ac, ur);
    }

    @PostMapping(value = {"/update/role/", "/update/role"})
    public AccountDTO updateUserRole(@RequestParam("oldusername") final String oldusername,@RequestParam("newusername") final String newusername, @RequestParam("newpassword") final String newpassword, @RequestParam("email") final String email)throws IllegalArgumentException {
        final Account ac = aService.getAccountByEmail(email);
        final UserRole ur = urService.getUserRoleByUserName(oldusername);
        String username;
        if(ur instanceof Manager){
            username = "Manager-" + newusername;
        }else if (ur instanceof Admin){
             username = "Admin-" + newusername;
        }else{
            username = "Developer-" + newusername; 
        }
        urService.updateUserRole(newpassword, username, ur);
        final List<UserRole> urList = urService.getAllUserRolesByAccount(ac);
        return convertToDTO(ac, urList);
    }

    @PostMapping({"/create/account", "/create/account/"})
    public AccountDTO createAccount(@RequestParam("accName") final String name,
            @RequestParam("accEmail") final String email,
            @RequestParam("description") final String description,
            @RequestParam("phoneNum") final int phoneNumber){
        
        final Account account = aService.createAccount(name, email, description, phoneNumber);
        final List<UserRole> userRoles = urService.getAllUserRolesByAccount(account);
        final AccountDTO accountDto = convertToDTO(account, userRoles);
        return accountDto;
    }

    @PostMapping({"/addrole/manager", "/addrole/manager/"})
    public AccountDTO addManagerRole(@RequestParam("userName") final String userName,
            @RequestParam("password") final String password,
            @RequestParam("email") final String email) {
        
        final Account account = aService.getAccountByEmail(email);
        if (account == null){
            throw new IllegalArgumentException("Account dosen't exists.");
        }
        final Manager manager = urService.createManagerRoleByAccount(password, userName, account);
        final List<UserRole> roles = urService.getAllUserRolesByAccount(account);
        final AccountDTO accountDto = convertToDTO(account, roles);
        return accountDto;
        
    }

    @PostMapping({"/addrole/admin", "/addrole/admin/"})
    public AccountDTO addAdminRole(@RequestParam("userName") final String userName,
            @RequestParam("password") final String password,
            @RequestParam("email") final String email) {
        
        final Account account = aService.getAccountByEmail(email);
        if (account == null){
            throw new IllegalArgumentException("Account dosen't exists.");
        }
        final Admin admine = urService.createAdminRoleByAccount(password, userName, account);
        final List<UserRole> roles = urService.getAllUserRolesByAccount(account);
        final AccountDTO accountDto = convertToDTO(account, roles);
        return accountDto;
        
    }




    private AccountDTO convertToDTO(final Account account, final List<UserRole> userrole ) {
        if (account == null) {
            throw new IllegalArgumentException("There is no account.");
        }
        
        final List<UserRoleDTO> urDTO = new ArrayList<>();
        for(final UserRole u : userrole){
            urDTO.add(convertToDTO(u));
        }
        final AccountDTO accDTO = new AccountDTO(account.getName(),account.getEmail(), account.getDescription(), account.getPhoneNumber(),urDTO);
        return accDTO;
    
    }

    private UserRoleDTO convertToDTO(final UserRole ur){
        if (ur == null) {
            throw new IllegalArgumentException("There is no userrole.");
        }
        UserRoleDTO urDTO;
        String username;
        if(ur instanceof Manager){
            username = ur.getUserName();
        }else if (ur instanceof Admin){
             username = ur.getUserName();
        }else{
            username = ur.getUserName(); 
        }
        urDTO = new UserRoleDTO (username, ur.getUser().getEmail());
        return urDTO;
    }
}