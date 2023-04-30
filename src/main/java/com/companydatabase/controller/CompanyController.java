package com.companydatabase.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.companydatabase.request.CompanyRequest;
import com.companydatabase.response.CompanyResponse;
import com.companydatabase.service.CompanyService;

@RestController
public class CompanyController {

    private static final Logger logger = LoggerFactory.getLogger(CompanyController.class);
	
    @Autowired
    private CompanyService companyService;

    
    // get all data
    @GetMapping("/companies")
    public ResponseEntity<List<CompanyResponse>> getAllCompanies() throws Exception {
        List<CompanyResponse> companies = companyService.getAllCompanies();
        return new ResponseEntity<List<CompanyResponse>>(companies, HttpStatus.OK);
    }

    // get data by id
    @GetMapping("/companies/{companyId}")
    public ResponseEntity<CompanyResponse> getCompanyById(@PathVariable Long companyId) throws Exception {
        Optional<CompanyResponse> optionalCompany = companyService.getCompanyById(companyId);
        if (optionalCompany.isPresent()) {
            return ResponseEntity.ok(optionalCompany.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // create company
    @PostMapping("/companies/post")
    public ResponseEntity<CompanyResponse> addCompany(@RequestBody CompanyRequest companyRequest) throws Exception {
        CompanyResponse createdCompany = companyService.createCompany(companyRequest);
        return new ResponseEntity<CompanyResponse>(createdCompany, HttpStatus.CREATED);
    }

    // update
    @PutMapping("/companies/update/{companyId}")
    public ResponseEntity<String> updateCompany(@PathVariable Long companyId,
            @RequestBody CompanyRequest companyRequest) throws Exception {
        String updateResult = companyService.updateCompany(companyId, companyRequest);
        logger.info("Company update result: {}", updateResult);
        return new ResponseEntity<String>(updateResult, HttpStatus.OK);
    }

    // delete data
    @DeleteMapping("/companies/delete/{companyId}")
    public ResponseEntity<String> deleteCompany(@PathVariable Long companyId) throws Exception {
        String deleteResult = companyService.deleteCompany(companyId);
        logger.info("Company delete result: {}", deleteResult);
        return new ResponseEntity <String> (deleteResult, HttpStatus.OK);
    }

}