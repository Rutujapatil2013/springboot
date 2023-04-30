package com.companydatabase.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.data.jpa.repository.JpaRepository;
import com.companydatabase.entity.Users;


public interface UserRepository extends JpaRepository<Users , Long>{

	List<Users> findByCompanyId(Long companyId);
	Optional<Users> findByUsername(String email);
//	Optional<User> findById(String username);
//	List<Users> findAll();
	Object save(User user);

}
