package ca.mcgill.ecse321.bugtracker.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.bugtracker.dto.AccountDTO;
import ca.mcgill.ecse321.bugtracker.dto.UserRoleDTO;
import ca.mcgill.ecse321.bugtracker.model.Account;
import ca.mcgill.ecse321.bugtracker.model.Admin;
import ca.mcgill.ecse321.bugtracker.model.Manager;
import ca.mcgill.ecse321.bugtracker.model.UserRole;

@CrossOrigin(origins = "*")
@RestController
public class AccountRestController {
    
    




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