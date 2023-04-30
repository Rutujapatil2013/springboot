package com.companydatabase.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.companydatabase.entity.Users;
import com.companydatabase.request.UserRequest;
import com.companydatabase.response.UserResponse;
import com.companydatabase.service.UserService;
import com.companydatabase.service.impl.UserServiceImpl;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@CrossOrigin
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<UserResponse>> getAllUsers() throws Exception {
        List<UserResponse> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
    
    
    @GetMapping("/getusers/{userId}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long userId) throws Exception {
        Optional<UserResponse> optionalUser = userService.getUsersById(userId);
        if (optionalUser.isPresent()) {
            return ResponseEntity.ok(optionalUser.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/users/company/{CompanyId}")
    public ResponseEntity<UserResponse> getUserByCompanyId(@PathVariable Long CompanyId) throws Exception {
        Optional<UserResponse> optionalUser = userService.getUsersByCompanyId(CompanyId);
        if (optionalUser.isPresent()) {
            return ResponseEntity.ok(optionalUser.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/allusers/excel/{companyId}")
    public ResponseEntity<List<UserResponse>> generateExcelReport(@PathVariable Long companyId) throws Exception{
        List<UserResponse> users = userService.generateExcel(companyId);
        return ResponseEntity.ok(users);
    }
    
    @GetMapping("/api/users/all/{companyId}")
    public ResponseEntity<List<UserResponse>> getAllUsersData(HttpServletResponse response, @PathVariable Long companyId) throws Exception{
    	List<UserResponse> users = userService.getAllUsers(response, companyId);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/api/users/present/{companyId}")
    public ResponseEntity<List<UserResponse>> getPresentUserData(HttpServletResponse response, @PathVariable Long companyId) throws Exception{
    	List<UserResponse> users = userService.getPresentUserData(response, companyId);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/api/users/deleted/{companyId}")
    public ResponseEntity<List<UserResponse>> getDeletedUserData(HttpServletResponse response, @PathVariable Long companyId) throws Exception{
    	List<UserResponse> users = userService.getDeletedUserData(response, companyId);
        return ResponseEntity.ok(users);
    }
    
    
    @PutMapping("/users/update/{userId}")
    public ResponseEntity<String> updateUser(@PathVariable Long userId, @RequestBody UserRequest userRequest)
            throws Exception {
        String response = userService.updateUser(userId, userRequest);
        return ResponseEntity.ok(response);
    }
    

    @PostMapping("/users/post")
    public ResponseEntity<UserResponse> addUser(@RequestBody UserRequest userRequest) throws Exception {
        UserResponse user = userService.createUser(userRequest);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/users/delete/{CompanyId}")
    public ResponseEntity<UserResponse> deleteUser(@PathVariable Long CompanyId) throws Exception {
    	UserResponse response = userService.deleteUser(CompanyId);
        return ResponseEntity.ok(response);
    }
    
    
    
    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody Users user){
       Optional<UserResponse> object =  userService.login(user.getEmail(), user.getPassword());
       return ResponseEntity.of(object);
    }


//    @PostMapping("/register")
//    public ResponseEntity<Object> registerUser(@RequestBody User user){
//        User savedUser = userService.registerUser(user);
//        return ResponseEntity.ok(savedUser);
//    }


//    @PutMapping("/{username}")
//    public ResponseEntity<Object> updateUser(@PathVariable String username, @RequestBody User user){
//        Optional<Object> updatedUser = userService.updatePasswordByUsername(username, user);
//        return ResponseEntity.ok(updatedUser.get());
//    }
    

}