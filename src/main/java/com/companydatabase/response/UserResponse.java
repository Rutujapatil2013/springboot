package com.companydatabase.response;

import com.companydatabase.entity.Company;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

	private Long userId;
    private String firstName;
    private String lastName;
    private String type;
    private String email;
    private String password;
    private Company company;
    private Boolean isDeleted;
    private Long companyId;
    
//	public Long getUserId() {
//	 return userId;
//	}
//	public void setUserId(Long userId) {
//		this.userId = userId;
//	}
//	public String getFirstName() {
//		return firstName;
//	}
//	public void setFirstName(String firstName) {
//		this.firstName = firstName;
//	}
//	public String getLastName() {
//		return lastName;
//	}
//	public void setLastName(String lastName) {
//		this.lastName = lastName;
//	}
//	public String getType() {
//		return type;
//	}
//	public void setType(String type) {
//		this.type = type;
//	}
	public String getEmail() {
		return email;
	}
//	public void setEmail(String email) {
//		this.email = email;
//	}
	public String getPassword() {
		return password;
	}
//	public void setPassword(String password) {
//		this.password = password;
//	}
//	public Company getCompany() {
//		return company;
//	}
//	public void setCompany(Company company) {
//		this.company = company;
//	}
//	public Boolean getIsDeleted() {
//		return isDeleted;
//	}
//	public void setIsDeleted(Boolean isDeleted) {
//		this.isDeleted = isDeleted;
//	}
//	public Long getCompanyId() {
//		return companyId;
//	}
//	public void setCompanyId(Long companyId) {
//		this.companyId = companyId;
//	}
		

}
