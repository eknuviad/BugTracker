package ca.mcgill.ecse321.bugtracker.dto;

import java.util.List;

public class AccountDTO {

    private String name;
    private String email;
    private String acDesc;
    private int phoneNumber;
    private List<UserRoleDTO> userRoles;


	public AccountDTO(String name, String email, String description, int phoneNumber, List<UserRoleDTO> urDTO) {
        this.name = name;
        this.email = email;
        this.acDesc = description;
        this.phoneNumber = phoneNumber;
        this.userRoles = urDTO;
	}
    
}