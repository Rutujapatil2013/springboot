package com.companydatabase.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.companydatabase.entity.Address;
import com.companydatabase.repository.AddressRepository;
import com.companydatabase.request.AddressRequest;
import com.companydatabase.response.AddressResponse;
import com.companydatabase.service.AddressService;
import com.companydatabase.transformer.AddressRequestConverter;
import com.companydatabase.transformer.AddressResponseConverter;

@Service
public class AddressServiceImpl implements AddressService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CompanyServiceImpl.class);
	
    @Autowired
    private AddressRepository addressRepository;

    @Override
    public List<AddressResponse> getAllRegistrations() throws Exception {
    	LOGGER.info("Fetching all Addressess");
        try {
            List<Address> address = addressRepository.findAll();
            return address.stream().map(AddressResponseConverter::convertToResponse).collect(Collectors.toList());
        } catch (Exception e) {
			LOGGER.error("Error while getting all addresses" , e);
            throw new Exception("Error while getting all addresses", e);
        }
    }

    @Override
    public Optional<AddressResponse> getAddressById(Long companyId) throws Exception {
        try {
            Optional<Address> addressOptional = addressRepository.findById(companyId);
            if (addressOptional.isPresent()) {
                AddressResponse addressResponse = AddressResponseConverter.convertToResponse(addressOptional.get());
                return Optional.of(addressResponse);
            } else {
                return Optional.empty();
            }
        } catch (Exception e) {
            // handle exception
            throw new Exception("Error while getting address by ID: " + companyId, e);
        }
    }

    
    @Override
    public AddressResponse createAddress(AddressRequest addressRequest) throws Exception {
        try {
            Address address = AddressRequestConverter.postAddressEntity(addressRequest);
            Address add = this.addressRepository.save(address);
            return AddressResponseConverter.convertToResponse(add);
        } catch (Exception e) {
            // handle exception
            throw new Exception("Error while creating address: " + addressRequest.toString(), e);
        }
    }

    
    @Override
    public String updateAddress(Long companyId, AddressRequest addressRequest) throws Exception {
        try {
            Optional<Address> optionalAddress = addressRepository.findById(companyId);
            if (optionalAddress.isPresent()) {
                Address address = optionalAddress.get();
                AddressRequestConverter.updateAddressEntity(address, addressRequest);
                addressRepository.save(address);
                return "Address updated successfully.";
            } else {
                return "Address not found.";
            }
        } catch (Exception e) {
            // handle exception
            throw new Exception("Error while updating address: " + addressRequest.toString(), e);
        }
    }

    
    @Override
    public String deleteAddress(Long companyId) throws Exception {
        try {
            Optional<Address> addressOptional = addressRepository.findById(companyId);
            if (addressOptional.isPresent()) {
                Address address = addressOptional.get();
                address.setIsDeleted(true); // set the deleted flag to true
                addressRepository.save(address);
                return "Address deleted successfully.";
            } else {
                Address address = new Address();
                address.setIsDeleted(false); // set the deleted flag to false
                addressRepository.save(address);
                return "Address not found.";
            }
        } catch (Exception e) {
            throw new Exception("Error occurred while deleting address with id: " + companyId, e);
        }
    }
}
