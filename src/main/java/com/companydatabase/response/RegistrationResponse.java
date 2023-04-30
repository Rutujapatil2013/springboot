package com.companydatabase.response;

import java.time.LocalDate;
import com.companydatabase.entity.Company;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationResponse {
	private String registrationId;
	private String legalName;
	private LocalDate DOR;
	private Long id;
    private Company company;
    private Boolean isDeleted;
    private Long companyId;

    
//	public String getRegistrationId() {
//		return registrationId;
//	}
//	public void setRegistrationId(String registrationId) {
//		this.registrationId = registrationId;
//	}
//	public String getLegalName() {
//		return legalName;
//	}
//	public void setLegalName(String legalName) {
//		this.legalName = legalName;
//	}
//	public LocalDate getDOR() {
//		return DOR;
//	}
//	public void setDOR(LocalDate dOR) {
//		DOR = dOR;
//	}
//
//
//	public Long getId() {
//		return id;
//	}
//	public void setId(Long id) {
//		this.id = id;
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
