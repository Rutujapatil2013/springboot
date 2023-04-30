package com.companydatabase.transformer;

import com.companydatabase.entity.Address;
import com.companydatabase.request.AddressRequest;

public class AddressRequestConverter {

	public static Address postAddressEntity(AddressRequest addressRequest) {
		
		Address address = new Address();
		address.setCompanyId(addressRequest.getCompanyId());
		address.setCity(addressRequest.getCity());
		address.setState(addressRequest.getState());
		address.setCountry(addressRequest.getCountry()); 
		address.setPincode(addressRequest.getPincode());
		address.setIsDeleted(addressRequest.getIsDeleted());
		address.setCompany(addressRequest.getCompany());
	    return address;
	}

	public static void updateAddressEntity(Address address, AddressRequest addressRequest) {
		address.setCompanyId(addressRequest.getCompanyId());
		address.setCity(addressRequest.getCity());
		address.setState(addressRequest.getState());
		address.setCountry(addressRequest.getCountry()); 
		address.setPincode(addressRequest.getPincode());
		address.setIsDeleted(addressRequest.getIsDeleted());
		address.setCompany(addressRequest.getCompany());
	}
	
	public static Address postAddressEntity(Address addressNew) {
			
			Address address = new Address();
			address.setCompanyId(addressNew.getCompanyId());
			address.setCity(addressNew.getCity());
			address.setState(addressNew.getState());
			address.setCountry(addressNew.getCountry()); 
			address.setPincode(addressNew.getPincode());
			address.setIsDeleted(addressNew.getIsDeleted());
			address.setCompany(addressNew.getCompany());
		    return address;
	}
	
	

}
