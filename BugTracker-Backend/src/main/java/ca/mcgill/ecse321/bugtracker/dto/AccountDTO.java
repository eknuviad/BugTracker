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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAcDesc() {
        return acDesc;
    }

    public void setAcDesc(String acDesc) {
        this.acDesc = acDesc;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<UserRoleDTO> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<UserRoleDTO> userRoles) {
        this.userRoles = userRoles;
    }
    
}