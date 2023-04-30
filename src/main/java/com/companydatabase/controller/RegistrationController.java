package com.companydatabase.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.companydatabase.request.RegistrationRequest;
import com.companydatabase.response.RegistrationResponse;
import com.companydatabase.service.RegistrationService;


@RestController
public class RegistrationController {

	@Autowired
	private RegistrationService registrationService;
	
	@GetMapping("/registration")
	public ResponseEntity<List<RegistrationResponse>> getAllRegistrations() throws Exception{
	    List<RegistrationResponse> registrations = registrationService.getAllRegistrations();
	    if(registrations.isEmpty()) {
	        return ResponseEntity.noContent().build();
	    }
	    return ResponseEntity.ok(registrations);
	}
	 
	@GetMapping("/registration/{companyId}")
	public ResponseEntity<RegistrationResponse> getRegistrationById(@PathVariable Long companyId) throws Exception {
	    Optional<RegistrationResponse> registrationOptional = registrationService.getRegistrationById(companyId);
	    if (registrationOptional.isPresent()) {
	        return ResponseEntity.ok(registrationOptional.get());
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
	 
	//create registration
	@PostMapping("/registration/post")
	public ResponseEntity<RegistrationResponse> addRegistration(@RequestBody RegistrationRequest registrationRequest) throws Exception {
	    RegistrationResponse registrationResponse = registrationService.createRegistration(registrationRequest);
	    return ResponseEntity.status(HttpStatus.CREATED).body(registrationResponse);
	}
	    
	//update registration
	@PutMapping("/registration/update/{companyId}")
	public ResponseEntity<String> updateRegistration(@PathVariable Long companyId, @RequestBody RegistrationRequest registrationRequest) throws Exception {
	    String response = registrationService.updateRegistration(companyId, registrationRequest);
	    return ResponseEntity.ok(response);
	}
	    
	//delete registration
	@DeleteMapping("/registration/delete/{companyId}")
	public ResponseEntity<String> deleteRegistration(@PathVariable Long companyId) throws Exception {
	    String response = registrationService.deleteRegistration(companyId);
	    return ResponseEntity.ok(response);
	}
}