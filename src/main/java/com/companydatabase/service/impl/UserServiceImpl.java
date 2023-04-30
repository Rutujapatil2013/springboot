package com.companydatabase.service.impl;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.http.ContentDisposition;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.companydatabase.entity.Users;
import com.companydatabase.repository.UserRepository;
import com.companydatabase.request.UserRequest;
import com.companydatabase.response.UserResponse;
import com.companydatabase.service.UserService;
import com.companydatabase.transformer.UserRequestConverter;
import com.companydatabase.transformer.UserResponseConverter;

import jakarta.servlet.ServletOutputStream;
import org.springframework.http.HttpHeaders;
import java.io.FileOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;



@Service
public class UserServiceImpl implements UserService{

	
    @Autowired
	private UserRepository userRepository;
    BCryptPasswordEncoder passwordEncoder;

	   
	@Override
	public List<UserResponse> getAllUsers() throws Exception {
		try {
			List<Users> users = userRepository.findAll();
			return users.stream().map(UserResponseConverter::convertToResponse).collect(Collectors.toList());
		} catch (Exception e) {
			// Handle exception
			throw new Exception("Error occurred while fetching all users", e);
		}
	}
	

	@Override
	public Optional<UserResponse> getUsersById(Long userId) throws Exception {
		try {
			Optional<Users> userOptional = userRepository.findById(userId);
			if (userOptional.isPresent()) {
				UserResponse userResponse = UserResponseConverter.convertToResponse(userOptional.get());
				return Optional.of(userResponse);
			} else {
				return Optional.empty();
			}
		} catch (Exception e) {
			// Handle exception
			throw new Exception("Error occurred while fetching user by ID: " + userId, e);
		}
	}
	
	@Override
	public Optional<UserResponse> getUsersByCompanyId(Long CompanyId) throws Exception {
		try {
			Optional<Users> userOptional = userRepository.findById(CompanyId);
			if (userOptional.isPresent()) {
				UserResponse userResponse = UserResponseConverter.convertToResponse(userOptional.get());
				return Optional.of(userResponse);
			} else {
				return Optional.empty();
			}
		} catch (Exception e) {
			// Handle exception
			throw new Exception("Error occurred while fetching user by ID: " + CompanyId, e);
		}
	}
	
	@Override
	public List<UserResponse> generateExcel(Long companyId) throws Exception
	{
	try{
		List<Users> users=userRepository.findByCompanyId(companyId);
		if (users == null || users.isEmpty()) {
            System.err.println("No users found for the given companyId");           
            return new ArrayList<>();
        }
		
		List<UserResponse> userResponse = new ArrayList<>();
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Users Info");
		
		LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        String timestamp = formatter.format(currentTime);
		
		HSSFRow header = sheet.createRow(0);
		header.createCell(0).setCellValue("companyId");
		header.createCell(1).setCellValue("userId");
		header.createCell(2).setCellValue("firstName");
		header.createCell(3).setCellValue("lastName");
		header.createCell(4).setCellValue("type");
		header.createCell(5).setCellValue("email");
		header.createCell(6).setCellValue("password");
		header.createCell(7).setCellValue("isDeleted");

		int dataRowIndex = 1;
		
		for (Users user : users) 
			if (!user.getIsDeleted())
			{
				HSSFRow dataRow = sheet.createRow(dataRowIndex);
				dataRow.createCell(0).setCellValue(user.getCompanyId());
				dataRow.createCell(1).setCellValue(user.getUserId());
				dataRow.createCell(2).setCellValue(user.getFirstName());
				dataRow.createCell(3).setCellValue(user.getLastName());
				dataRow.createCell(4).setCellValue(user.getType());
				dataRow.createCell(5).setCellValue(user.getEmail());
				dataRow.createCell(6).setCellValue(user.getPassword());
				dataRow.createCell(7).setCellValue(user.getIsDeleted());
				dataRowIndex++;
			}
		 
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
	    headers.setContentDisposition(ContentDisposition.builder("attachment").filename("users_" + timestamp + ".xls").build());
	
//	    FileOutputStream fileOut = new FileOutputStream(filename);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        workbook.write(stream);
		workbook.close();
		stream.close();
		return userResponse;
		}
	
		catch(Exception e) {
			throw new Exception("Error occured while generating excel sheet",e);
		}
	}
	

	@Override
	public List<UserResponse> getAllUsers(HttpServletResponse response,Long companyId) throws Exception {
		try{
			List<Users> users=userRepository.findByCompanyId(companyId);
			
			List<UserResponse> userResponse = new ArrayList<>();
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet("Users Info");
			
			LocalDateTime currentTime = LocalDateTime.now();
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
	        String timestamp = formatter.format(currentTime);
			
			HSSFRow header = sheet.createRow(0);
			header.createCell(0).setCellValue("companyId");
			header.createCell(1).setCellValue("userId");
			header.createCell(2).setCellValue("firstName");
			header.createCell(3).setCellValue("lastName");
			header.createCell(4).setCellValue("type");
			header.createCell(5).setCellValue("email");
			header.createCell(6).setCellValue("password");
			header.createCell(7).setCellValue("isDeleted");

			int dataRowIndex = 1;
			
			for (Users i : users)
				{
					HSSFRow dataRow = sheet.createRow(dataRowIndex);
					dataRow.createCell(0).setCellValue(i.getCompanyId());
					dataRow.createCell(1).setCellValue(i.getUserId());
					dataRow.createCell(2).setCellValue(i.getFirstName());
					dataRow.createCell(3).setCellValue(i.getLastName());
					dataRow.createCell(4).setCellValue(i.getType());
					dataRow.createCell(5).setCellValue(i.getEmail());
					dataRow.createCell(6).setCellValue(i.getPassword());
					dataRow.createCell(7).setCellValue(i.getIsDeleted());
					dataRowIndex++;
				}
			
			response.setContentType("application/octet-stream");
	        String headerKey = "Content-Disposition";
	        String headerValue = "attachment;filename=users_"+timestamp+".xls";
	        response.setHeader(headerKey, headerValue);
			
			ServletOutputStream fileOut = response.getOutputStream();
			workbook.write(fileOut);
			workbook.close();
			fileOut.close();
			return userResponse;
			}
		
			catch(Exception e) {
				throw new Exception("Error occured while generating excel sheet",e);
			}
	}

	
	@Override
	public List<UserResponse> getPresentUserData(HttpServletResponse response,Long companyId) throws Exception {
		try{
			List<Users> users=userRepository.findByCompanyId(companyId);
			
			List<UserResponse> userResponse = new ArrayList<>();
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet("Users Info");
			
			LocalDateTime currentTime = LocalDateTime.now();
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
	        String timestamp = formatter.format(currentTime);
			
			HSSFRow header = sheet.createRow(0);
			header.createCell(0).setCellValue("companyId");
			header.createCell(1).setCellValue("userId");
			header.createCell(2).setCellValue("firstName");
			header.createCell(3).setCellValue("lastName");
			header.createCell(4).setCellValue("type");
			header.createCell(5).setCellValue("email");
			header.createCell(6).setCellValue("password");
			header.createCell(7).setCellValue("isDeleted");

			int dataRowIndex = 1;
			
			for (Users i : users)
				if(!i.getIsDeleted())
				{
					HSSFRow dataRow = sheet.createRow(dataRowIndex);
					dataRow.createCell(0).setCellValue(i.getCompanyId());
					dataRow.createCell(1).setCellValue(i.getUserId());
					dataRow.createCell(2).setCellValue(i.getFirstName());
					dataRow.createCell(3).setCellValue(i.getLastName());
					dataRow.createCell(4).setCellValue(i.getType());
					dataRow.createCell(5).setCellValue(i.getEmail());
					dataRow.createCell(6).setCellValue(i.getPassword());
					dataRow.createCell(7).setCellValue(i.getIsDeleted());
					dataRowIndex++;
				}
			
			response.setContentType("application/octet-stream");
	        String headerKey = "Content-Disposition";
	        String headerValue = "attachment;filename=users_"+timestamp+".xls";
	        response.setHeader(headerKey, headerValue);
			
			ServletOutputStream fileOut = response.getOutputStream();
			workbook.write(fileOut);
			workbook.close();
			fileOut.close();
			return userResponse;
			}
		
			catch(Exception e) {
				throw new Exception("Error occured while generating excel sheet",e);
			}
	}
	

	@Override
	public List<UserResponse> getDeletedUserData(HttpServletResponse response,Long companyId) throws Exception {
		try{
			List<Users> users=userRepository.findByCompanyId(companyId);
			
			List<UserResponse> userResponse = new ArrayList<>();
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet("Users Info");
			
			LocalDateTime currentTime = LocalDateTime.now();
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
	        String timestamp = formatter.format(currentTime);
			
			HSSFRow header = sheet.createRow(0);
			header.createCell(0).setCellValue("companyId");
			header.createCell(1).setCellValue("userId");
			header.createCell(2).setCellValue("firstName");
			header.createCell(3).setCellValue("lastName");
			header.createCell(4).setCellValue("type");
			header.createCell(5).setCellValue("email");
			header.createCell(6).setCellValue("password");
			header.createCell(7).setCellValue("isDeleted");

			int dataRowIndex = 1;
			
			for (Users i : users)
				if(i.getIsDeleted())
				{
					HSSFRow dataRow = sheet.createRow(dataRowIndex);
					dataRow.createCell(0).setCellValue(i.getCompanyId());
					dataRow.createCell(1).setCellValue(i.getUserId());
					dataRow.createCell(2).setCellValue(i.getFirstName());
					dataRow.createCell(3).setCellValue(i.getLastName());
					dataRow.createCell(4).setCellValue(i.getType());
					dataRow.createCell(5).setCellValue(i.getEmail());
					dataRow.createCell(6).setCellValue(i.getPassword());
					dataRow.createCell(7).setCellValue(i.getIsDeleted());
					dataRowIndex++;
				}
			
			response.setContentType("application/octet-stream");
	        String headerKey = "Content-Disposition";
	        String headerValue = "attachment;filename=users_"+timestamp+".xls";
	        response.setHeader(headerKey, headerValue);
			
			ServletOutputStream fileOut = response.getOutputStream();
			workbook.write(fileOut);
			workbook.close();
			fileOut.close();
			return userResponse;
			}
		
			catch(Exception e) {
				throw new Exception("Error occured while generating excel sheet",e);
			}
	}
	
	
	@Override
	public String updateUser(Long userId, UserRequest userRequest) throws Exception {
		try {
			Optional<Users> optionaUser = userRepository.findById(userId);
			if (optionaUser.isPresent()) {
				Users user = optionaUser.get();
				UserRequestConverter.updateUsersEntity(user, userRequest);
				userRepository.save(user);
				return "User updated successfully.";
			} else {
				return "User not found.";
			}
		} catch (Exception e) {
			// Handle exception
			throw new Exception("Error occurred while updating user with ID: " + userId, e);
		}
	}
	
	
	@Override
	public UserResponse createUser(UserRequest userRequest) throws Exception {
		try {
			Users user=UserRequestConverter.postUsersEntity(userRequest);
			Users users=this.userRepository.save(user);
			return UserResponseConverter.convertToResponse(users);
		} catch (Exception e) {
			// Handle exception
			throw new Exception("Error occurred while creating new user", e);
		}
	}

	
	@Override
	public UserResponse deleteUser(Long CompanyId) throws Exception {
	    try {
	        Optional<Users> optionalUser = userRepository.findById(CompanyId);
	        if (optionalUser.isPresent()) {
	            Users user = optionalUser.get();
	            userRepository.deleteById(CompanyId);
	            user.setIsDeleted(true); // set the deleted flag to true
	            return UserResponseConverter.convertToResponse(user);
	        } else {
	            UserResponse userResponse = new UserResponse();
	            userResponse.setIsDeleted(false); // set the deleted flag to false
	            throw new Exception("User not found with ID: " + CompanyId);
	        }
	    } catch (Exception e) {
	        // Handle exception
	        throw new Exception("Error occurred while deleting user with ID: " + CompanyId, e);
	    }
	}
	
	
	
	//login 
	
//	public Optional<UserResponse> login(String email, String password){
//        Users user = userRepository.findByUsername((email)).orElseThrow(() -> new RuntimeException("Username not found"));
//
//        if(passwordEncoder.matches(password, user.getPassword())){
//            return Optional.of(user);
//        }
//        return Optional.of("Your password is wrong");
//
//    }
	
	
	public Optional<UserResponse> login(String email, String password) throws Exception {
		try {
			Optional<Users> userOptional = userRepository.findByUsername(email);
			if (passwordEncoder.matches(password, userOptional.getPassword())) {
				UserResponse userResponse = UserResponseConverter.convertToResponse(userOptional.get());
				return Optional.of(userResponse);
			} else {
				return Optional.empty();
			}
		} catch (Exception e) {
			// Handle exception
			throw new Exception("Error occurred while fetching user by ID: " + userId, e);
		}
	}


//    public Optional<Object> updatePasswordByUsername(String username,User userToUpdate){
//        User user = userRepository.findByUsername((username)).orElseThrow(() -> new RuntimeException("Username not found"));
//
//        user.setPassword(passwordEncoder.encode(userToUpdate.getPassword()));
//
//        return Optional.of(userRepository.save(user));
//    }

	

}

