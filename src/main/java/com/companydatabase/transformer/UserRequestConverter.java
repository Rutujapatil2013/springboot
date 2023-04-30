package com.companydatabase.transformer;

import com.companydatabase.entity.Users;
import com.companydatabase.request.UserRequest;

public class UserRequestConverter {

	public static Users updateUsersEntity(Users user, UserRequest userRequest) {
		user.setUserId(userRequest.getUserId());
		user.setFirstName(userRequest.getFirstName());
		user.setLastName(userRequest.getLastName());
		user.setType(userRequest.getType()); 
		user.setEmail(userRequest.getEmail()); 
		user.setPassword(userRequest.getPassword());
//		user.setCompanyId(userRequest.getCompanyId());
		user.setIsDeleted(userRequest.getIsDeleted());
		user.setCompany(userRequest.getCompany());
		return user;
	}

	public static Users postUsersEntity(UserRequest userRequest) {
		Users user=new Users();
		user.setUserId(userRequest.getUserId());
		user.setFirstName(userRequest.getFirstName());
		user.setLastName(userRequest.getLastName());
		user.setType(userRequest.getType()); 
		user.setEmail(userRequest.getEmail()); 
		user.setPassword(userRequest.getPassword());
//		user.setCompanyId(userRequest.getCompanyId());
		user.setIsDeleted(userRequest.getIsDeleted());
		user.setCompany(userRequest.getCompany());
		return user;
	}

}
