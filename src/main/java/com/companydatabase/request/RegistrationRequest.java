package com.companydatabase.request;

import java.time.LocalDate;
import com.companydatabase.entity.Company;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequest {

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
//
//	public String getLegalName() {
//		return legalName;
//	}
//
//	public LocalDate getDOR() {
//		return DOR;
//	}
//
//	public Long getId() {
//		return id;
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
//	
	
	


    
}
