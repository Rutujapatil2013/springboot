package com.companydatabase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.companydatabase.repository.*;


@EnableScheduling
@SpringBootApplication
public class CompanyDatabaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompanyDatabaseApplication.class, args);
	}
	
	@Autowired
    private UserRepository userRepository;
	
    public void run(String... args) throws Exception {
    	
    }


    @Bean
    public BCryptPasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
