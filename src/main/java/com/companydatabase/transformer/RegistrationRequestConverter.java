package com.companydatabase.transformer;

import com.companydatabase.entity.Registration;
import com.companydatabase.request.RegistrationRequest;


public class RegistrationRequestConverter {
	
	public static Registration postRegistrationEntity(RegistrationRequest request) {
			
		Registration registration = new Registration();
		registration.setRegistrationId(request.getRegistrationId());
		registration.setId(request.getId());
		registration.setLegalName(request.getLegalName());
		registration.setDOR(request.getDOR());
		registration.setCompany(request.getCompany());
		registration.setCompanyId(request.getCompanyId());
		registration.setIsDeleted(request.getIsDeleted());
	        return registration;
	    }
	
	public static Registration updateRegistrationEntity(Registration registration, RegistrationRequest registrationRequest) {
		
		registration.setRegistrationId(registrationRequest.getRegistrationId());
		registration.setId(registrationRequest.getId());
		registration.setLegalName(registrationRequest.getLegalName());
		registration.setDOR(registration.getDOR());
		registration.setIsDeleted(registrationRequest.getIsDeleted());
		registration.setCompany(registrationRequest.getCompany());
		registration.setCompanyId(registrationRequest.getCompanyId());
		registration.setIsDeleted(registrationRequest.getIsDeleted());
	    return registration;
		
	}
}
