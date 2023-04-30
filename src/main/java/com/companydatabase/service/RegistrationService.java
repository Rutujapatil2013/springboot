package com.companydatabase.service;

import java.util.List;
import java.util.Optional;

import com.companydatabase.request.RegistrationRequest;
import com.companydatabase.response.RegistrationResponse;


public interface RegistrationService {
	 List<RegistrationResponse> getAllRegistrations() throws Exception;
	 
	 public Optional<RegistrationResponse> getRegistrationById(Long companyId) throws Exception;
	 
	 RegistrationResponse createRegistration(RegistrationRequest registrationRequest) throws Exception;
	 
	 String updateRegistration(Long companyId, RegistrationRequest registrationRequest) throws Exception;
	 
	 String deleteRegistration(Long companyId) throws Exception;
	
}
