package com.companydatabase.service;

import java.util.List;
import java.util.Optional;

import com.companydatabase.entity.Users;
import com.companydatabase.request.UserRequest;
import com.companydatabase.response.UserResponse;

import jakarta.servlet.http.HttpServletResponse;

public interface UserService {

	List<UserResponse> getAllUsers() throws Exception;
	Optional<UserResponse> getUsersById(Long userId) throws Exception;
	Optional<UserResponse> getUsersByCompanyId(Long CompanyId) throws Exception;
	String updateUser(Long CompanyId, UserRequest userRequest) throws Exception;
	UserResponse deleteUser(Long CompanyId) throws Exception;
	UserResponse createUser(UserRequest userRequest) throws Exception;
	List<UserResponse> generateExcel(Long companyId) throws Exception;
	List<UserResponse> getAllUsers(HttpServletResponse response,Long companyId) throws Exception;
	List<UserResponse> getPresentUserData(HttpServletResponse response,Long companyId) throws Exception;
	List<UserResponse> getDeletedUserData(HttpServletResponse response,Long companyId) throws Exception;
//	getUsername
	Optional<UserResponse> login(String email, String password) throws Exception;

//	List<UserResponse> getAllUsers(HttpServletResponse response, Long companyId);
}
