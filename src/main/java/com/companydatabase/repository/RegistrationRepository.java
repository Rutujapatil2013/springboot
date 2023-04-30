package com.companydatabase.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.companydatabase.entity.Registration;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {
//    List<Registration> findAll();
//	Optional<Registration> findById(Long id);

}
