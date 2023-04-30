package com.companydatabase.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.companydatabase.request.AddressRequest;
import com.companydatabase.response.AddressResponse;
import com.companydatabase.service.AddressService;


@RestController
public class AddressController {
	
    private static final Logger logger = LoggerFactory.getLogger(AddressController.class);
    
    @Value("${default.key:'ddfgfggfg'}")
    private String defaultValue;
    
	@Autowired
	private AddressService addressService;
	
	@GetMapping("/address/value")
	public String getAddress(){
		return defaultValue;
	}
	
	@GetMapping("/address")
	public ResponseEntity<List<AddressResponse>> getAllAddress() throws Exception {
		List<AddressResponse> addresses = addressService.getAllRegistrations();
		logger.error("FATAL ERROR");
		return ResponseEntity.ok().body(addresses);
	}

	@GetMapping("/address/{companyId}")
	public ResponseEntity<AddressResponse> getAddressById(@PathVariable Long companyId) throws Exception {
		Optional<AddressResponse> optionalAddress = addressService.getAddressById(companyId);
		if (optionalAddress.isPresent()) {
			AddressResponse address = optionalAddress.get();
			return ResponseEntity.ok().body(address);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("/address/post")
	public ResponseEntity<AddressResponse> addAddress(@RequestBody AddressRequest addressRequest) throws Exception {
		AddressResponse address = addressService.createAddress(addressRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body(address);
	}

	@PutMapping("/address/update/{companyId}")
	public ResponseEntity<String> updateAddress(@PathVariable Long companyId,
			@RequestBody AddressRequest addressRequest) throws Exception {
		String updateResult = addressService.updateAddress(companyId, addressRequest);
        logger.info("Address update result: {}", updateResult);
		return ResponseEntity.ok().body(updateResult);
	}

	@DeleteMapping("/address/delete/{companyId}")
	public ResponseEntity<String> deleteAddress(@PathVariable Long companyId) throws Exception {
		String deleteResult = addressService.deleteAddress(companyId);
        logger.info("Address update result: {}", deleteResult);
		return ResponseEntity.ok().body(deleteResult);
	}
}