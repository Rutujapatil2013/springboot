package com.companydatabase.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.companydatabase.entity.Registration;
import com.companydatabase.repository.RegistrationRepository;
import com.companydatabase.request.RegistrationRequest;
import com.companydatabase.response.RegistrationResponse;
import com.companydatabase.service.RegistrationService;
import com.companydatabase.transformer.RegistrationRequestConverter;
import com.companydatabase.transformer.RegistrationResponseConverter;


@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    private RegistrationRepository registrationRepository;

    @Override
    public List<RegistrationResponse> getAllRegistrations() throws Exception {
        try {
            List<Registration> companies = registrationRepository.findAll();
            return companies.stream().map(RegistrationResponseConverter::convertToResponse).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Failed to get all registrations");
        }
    }

    @Override
    public Optional<RegistrationResponse> getRegistrationById(Long companyId) throws Exception {
        try {
            Optional<Registration> registrationOptional = registrationRepository.findById(companyId);
            if (registrationOptional.isPresent()) {
                RegistrationResponse registrationResponse = RegistrationResponseConverter.convertToResponse(registrationOptional.get());
                return Optional.of(registrationResponse);
            } else {
                return Optional.empty();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Failed to get registration by id");
        }
    }

    @Override
    public RegistrationResponse createRegistration(RegistrationRequest registrationRequest) throws Exception {
        try {
            Registration registration=RegistrationRequestConverter.postRegistrationEntity(registrationRequest);
            Registration reg=this.registrationRepository.save(registration);
            return RegistrationResponseConverter.convertToResponse(reg);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Failed to create registration");
        }
    }
    
    
 // update company
    @Override
    public String updateRegistration(Long companyId, RegistrationRequest registrationRequest) throws Exception {
        try {
            Optional<Registration> optionalRegistration = registrationRepository.findById(companyId);
            if (optionalRegistration.isPresent()) {
            	Registration registration = optionalRegistration.get();
            	RegistrationRequestConverter.updateRegistrationEntity(registration, registrationRequest);
                registrationRepository.save(registration);
                return "Registration updated successfully.";
            } else {
                return "registration not found.";
            }
        } catch (Exception e) {
            throw new Exception("Error occurred while updating registration with id: " + companyId, e);
        }
    }

    @Override
    public String deleteRegistration(Long companyId) throws Exception {
        try {
            Optional<Registration> optionalRegistration = registrationRepository.findById(companyId);
            if (optionalRegistration.isPresent()) {
                Registration registration = optionalRegistration.get();
                registration.setIsDeleted(true); // set the deleted flag
                registrationRepository.save(registration);
                return "Registration deleted successfully.";
            } else {
                Registration registration = new Registration();
                registration.setIsDeleted(false); // set the deleted flag to the opposite of the input value
                registrationRepository.save(registration);
                return "Registration not found.";
            }
        } catch (Exception e) {
            throw new Exception("Error occurred while deleting registration with id: " + companyId, e);
        }
    }
 
    

}
