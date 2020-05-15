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
import ca.mcgill.ecse321.bugtracker.model.Developer;
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
    public AccountDTO createDeveloperByAccount(@RequestParam("username")  String username, @RequestParam("password")  String password, @RequestParam("email")  String email)throws IllegalArgumentException {
         Account ac = aService.getAccountByEmail(email);
         String devName = "Developer-" + username;
        Developer developer = urService.createDeveloperRoleByAccount(password, devName, ac);
         List<UserRole> ur = urService.getAllUserRolesByAccount(ac);
        return convertToDTO(ac, ur);
    }

    @GetMapping(value = {"/account/email/", "/account/email"})
    public AccountDTO getAccountByEmail(@RequestParam("email")  String email)throws IllegalArgumentException {
         Account ac = aService.getAccountByEmail(email);
         List<UserRole> ur = urService.getAllUserRolesByAccount(ac);
        return convertToDTO(ac, ur);
    }

    @PostMapping(value = {"/update/role/", "/update/role"})
    public AccountDTO updateUserRole(@RequestParam("oldusername")  String oldusername,@RequestParam("newusername")  String newusername, @RequestParam("newpassword")  String newpassword, @RequestParam("email")  String email)throws IllegalArgumentException {
         Account ac = aService.getAccountByEmail(email);
         UserRole ur = urService.getUserRoleByUserName(oldusername);
        String username;
        if(ur instanceof Manager){
            username = "Manager-" + newusername;
        }else if (ur instanceof Admin){
             username = "Admin-" + newusername;
        }else{
            username = "Developer-" + newusername; 
        }
        urService.updateUserRole(newpassword, oldusername, ur);
         List<UserRole> urList = urService.getAllUserRolesByAccount(ac);
        return convertToDTO(ac, urList);
    }

    @PostMapping({"/create/account", "/create/account/"})
    public AccountDTO createAccount(@RequestParam("accName")  String name,
            @RequestParam("accEmail")  String email,
            @RequestParam("description")  String description,
            @RequestParam("phoneNum")  int phoneNumber){
        
         Account account = aService.createAccount(name, email, description, phoneNumber);
         List<UserRole> userRoles = urService.getAllUserRolesByAccount(account);
         AccountDTO accountDto = convertToDTO(account, userRoles);
        return accountDto;
    }

    @PostMapping({"/addrole/manager", "/addrole/manager/"})
    public AccountDTO addManagerRole(@RequestParam("userName")  String userName,
            @RequestParam("password")  String password,
            @RequestParam("email")  String email) {
        
         Account account = aService.getAccountByEmail(email);
        if (account == null){
            throw new IllegalArgumentException("Account dosen't exists.");
        }
        String managerName = "Manager-" + userName;
         Manager manager = urService.createManagerRoleByAccount(password, managerName, account);
         List<UserRole> roles = urService.getAllUserRolesByAccount(account);
         AccountDTO accountDto = convertToDTO(account, roles);
        return accountDto;
        
    }

    @PostMapping({"/addrole/admin", "/addrole/admin/"})
    public AccountDTO addAdminRole(@RequestParam("userName")  String userName,
            @RequestParam("password")  String password,
            @RequestParam("email")  String email) {
        
         Account account = aService.getAccountByEmail(email);
        if (account == null){
            throw new IllegalArgumentException("Account dosen't exists.");
        }
        String adminName = "Admin-" + userName;
         Admin admine = urService.createAdminRoleByAccount(password, adminName, account);
         List<UserRole> roles = urService.getAllUserRolesByAccount(account);
         AccountDTO accountDto = convertToDTO(account, roles);
        return accountDto;
        
    }




    private AccountDTO convertToDTO( Account account,  List<UserRole> userrole ) {
        if (account == null) {
            throw new IllegalArgumentException("There is no account.");
        }
        
         List<UserRoleDTO> urDTO = new ArrayList<>();

         if (userrole == null){
             urDTO = null;
         } else {
            for( UserRole u : userrole){
                urDTO.add(convertToDTO(u));
            }
         }
  
         AccountDTO accDTO = new AccountDTO(account.getName(),account.getEmail(), account.getDescription(), account.getPhoneNumber(),urDTO);
        return accDTO;
    
    }

    private UserRoleDTO convertToDTO( UserRole ur){
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