package ca.mcgill.ecse321.bugtracker.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.bugtracker.dto.AccountDTO;
import ca.mcgill.ecse321.bugtracker.model.Account;
import ca.mcgill.ecse321.bugtracker.model.UserRole;

@CrossOrigin(origins = "*")
@RestController
public class AccountRestController {
    
    




    private AccountDTO convertToDTO(Account account, List<UserRole> userrole ) {
        if (account == null) {
            throw new IllegalArgumentException("There is no account.");
        }
        // List<UserRoleDTO> urDTO = converttoDTO(userrole);
        // AccountDTO accDTO = new AccountDTO(account.getName(),account.getEmail(), account.getDescription(), account.getPhoneNumber(),urDTO);
        // return accDTO;
        return null;
    
    }
}