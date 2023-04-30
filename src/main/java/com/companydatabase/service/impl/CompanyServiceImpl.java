package com.companydatabase.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.companydatabase.entity.Company;
import com.companydatabase.repository.CompanyRepository;
import com.companydatabase.request.CompanyRequest;
import com.companydatabase.response.CompanyResponse;
import com.companydatabase.service.CompanyService;
import com.companydatabase.transformer.RequestConverter;
import com.companydatabase.transformer.ResponceConverter;

@Service
public class CompanyServiceImpl implements CompanyService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CompanyServiceImpl.class);
	
    @Autowired
    private CompanyRepository companyRepository;
    public  static boolean ans;

    // get all company
    @Override
    public List<CompanyResponse> getAllCompanies() throws Exception {
        try {
        	LOGGER.info("Fetching all companies");
            List<Company> companies = companyRepository.findAll();
            return companies.stream().map(ResponceConverter::convertToResponse).collect(Collectors.toList());
            
        } catch (Exception e) {
			LOGGER.error("Error while getting all companies" , e);
            throw new Exception("Error occurred while fetching companies.", e);
        }
    }

    @Override
    public Optional<CompanyResponse> getCompanyById(Long companyId) throws Exception {
        try {
			LOGGER.info("Fetching company by id {}" ,companyId);
            Optional<Company> companyOptional = companyRepository.findById(companyId);
            if (companyOptional.isPresent()) {
                CompanyResponse companyResponse = ResponceConverter.convertToResponse(companyOptional.get());
                return Optional.of(companyResponse);
            } else {
                return Optional.empty();
            }
        } catch (Exception e) {
			 LOGGER.error("Error while fetching company by Id {} " , companyId , e);
            throw new Exception("Error occurred while fetching company with id: " + companyId, e);
        }
    }
    
    // create company
    @Override
    public CompanyResponse createCompany(CompanyRequest companyRequest) throws Exception {
        try {
			LOGGER.info("Creating new company");
            Company company = RequestConverter.postCompanyEntity(companyRequest);
            Company com = this.companyRepository.save(company);
            return ResponceConverter.convertToResponse(com);
        } catch (Exception e) {
			LOGGER.error("Error while creating new company",e);
            throw new Exception("Error occurred while creating company.", e);
        }
    }
    

    // update company
    @Override
    public String updateCompany(Long companyId, CompanyRequest companyRequest) throws Exception {
        try {
            LOGGER.info("Updating company with ID {}" , companyId);
            Optional<Company> optionalCompany = companyRepository.findById(companyId);
            if (optionalCompany.isPresent()) {
                Company company = optionalCompany.get();
                RequestConverter.updateCompanyEntity(company, companyRequest);
                companyRepository.save(company);
                return "Company updated successfully.";
            } else {
                return "Company not found.";
            }
        } catch (Exception e) {
			LOGGER.error("Error while updating company with ID {} " , companyId,e);
            throw new Exception("Error occurred while updating company with id: " + companyId, e);
        }
    }

    @Override
    public String deleteCompany(Long companyId) throws Exception {
        try {
			LOGGER.info("Deleting company with ID {}" , companyId);
            Optional<Company> optionalCompany = companyRepository.findById(companyId);
            if (optionalCompany.isPresent()) {
                Company company = optionalCompany.get();
                company.setIsDeleted(true); // set the deleted flag
                company.getUsers().forEach(user -> user.setIsDeleted(true)); 
                company.getAddress().setIsDeleted(true);
                company.getRegistration().setIsDeleted(true);
                companyRepository.save(company);
                return "Company deleted successfully.";
            } else {
                return "Company not found.";
            }
        } catch (Exception e) {
			LOGGER.info("Error deleting company with ID {}" , companyId,e);
            throw new Exception("Error occurred while deleting company with id: " + companyId, e);
        }
    }

   
}
