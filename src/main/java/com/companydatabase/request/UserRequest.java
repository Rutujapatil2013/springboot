package com.companydatabase.request;

import com.companydatabase.entity.Company;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

	
	private Long userId;
    private String firstName;
    private String lastName;
    private String type;
    private String email;
    private String password;
    private Company company;
    private Boolean isDeleted;
//    private Long companyId;
    
//	public Long getUserId() {
//	 return userId;
//	}
//
//	public String getFirstName() {
//		return firstName;
//	}
//
//	public String getLastName() {
//		return lastName;
//	}
//
//	public String getType() {
//		return type;
//	}
//
	public String getEmail() {
		return email;
	}
//
//	public String getPassword() {
//		return password;
//	}
//
//	public Company getCompany() {
//		return company;
//	}
//
//	public Boolean getIsDeleted() {
//		return isDeleted;
//	}
//
//	public Long getCompanyId() {
//		return companyId;
//	}

	
	    
}
