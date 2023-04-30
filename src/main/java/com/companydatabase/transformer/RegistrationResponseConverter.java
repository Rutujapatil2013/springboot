package com.companydatabase.transformer;

import com.companydatabase.entity.Registration;
import com.companydatabase.response.RegistrationResponse;

public class RegistrationResponseConverter {

    public static RegistrationResponse convertToResponse(Registration registration) {
    	
        RegistrationResponse registrationResponse = new RegistrationResponse();
        registrationResponse.setRegistrationId(registration.getRegistrationId());
        registrationResponse.setId(registration.getId());
        registrationResponse.setLegalName(registration.getLegalName());
    	registrationResponse.setDOR(registration.getDOR());
    	registrationResponse.setIsDeleted(registration.getIsDeleted());
    	registrationResponse.setCompanyId(registration.getCompanyId());
        registrationResponse.setCompany(registration.getCompany());
        return registrationResponse;
    }
}
