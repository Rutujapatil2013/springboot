package com.companydatabase.transformer;

import com.companydatabase.entity.Company;
import com.companydatabase.request.CompanyRequest;

public class RequestConverter {
	public static Company postCompanyEntity(CompanyRequest companyRequest) {
		
        Company company = new Company();
        
        company.setCompanyId(companyRequest.getCompanyId());
        company.setCompanyName(companyRequest.getCompanyName());
        company.setEmailId(companyRequest.getEmailId());
        company.setPhoneNumber(companyRequest.getPhoneNumber());
        company.setIsDeleted(companyRequest.getIsDeleted());
        company.setRegistration((companyRequest.getRegistration()));
        company.setUsers(companyRequest.getUsers());
        company.setAddress(companyRequest.getAddress());
        return company;
    }

	public static Company updateCompanyEntity(Company company, CompanyRequest companyRequest) {
		company.setCompanyId(companyRequest.getCompanyId());
		company.setCompanyName(companyRequest.getCompanyName());
		  company.setEmailId(companyRequest.getEmailId());
	        company.setPhoneNumber(companyRequest.getPhoneNumber());
	        company.setIsDeleted(companyRequest.getIsDeleted());
	        company.setRegistration(companyRequest.getRegistration());
	        company.setUsers(companyRequest.getUsers());
	        company.setAddress(companyRequest.getAddress());
        return company;
	}
	

}

