package com.companydatabase.service;

import java.util.List;
import java.util.Optional;

import com.companydatabase.request.AddressRequest;
import com.companydatabase.response.AddressResponse;

public interface AddressService {

	List<AddressResponse> getAllRegistrations() throws Exception;

	Optional<AddressResponse> getAddressById(Long companyId) throws Exception;

	AddressResponse createAddress(AddressRequest addressRequest) throws Exception;

	String updateAddress(Long companyId, AddressRequest addressRequest) throws Exception;

	String deleteAddress(Long companyId) throws Exception;

}
