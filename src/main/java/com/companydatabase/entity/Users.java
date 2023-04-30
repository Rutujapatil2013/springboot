package com.companydatabase.entity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="users")
public class Users {
   
	     
	    @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Long userId;
	    
	    
	    private Long companyId;
	    private String firstName;
	    private String lastName;
	    private String type;
	    private String email;
	    private String password;
	    private Boolean isDeleted;
	   
	    @ManyToOne(cascade = CascadeType.ALL)
	    @JoinColumn(name="fk_user_id")
	    @JsonBackReference
	    private Company company;
	    
//	    public Company getCompany() {
// 			return company;
// 		}
//
// 		public void setCompany(Company company) {
// 			this.company = company;
// 		}
// 		
//		public Long getUserId() {
//			return userId;
//		}
//
//
//		public void setUserId(Long userId) {
//			this.userId = userId;
//		}
//
//
//		public String getFirstName() {
//			return firstName;
//		}
//
//
//		public void setFirstName(String firstName) {
//			this.firstName = firstName;
//		}
//
//
//		public String getLastName() {
//			return lastName;
//		}
//
//
//		public void setLastName(String lastName) {
//			this.lastName = lastName;
//		}
//
//
//		public String getType() {
//			return type;
//		}
//
//
//		public void setType(String type) {
//			this.type = type;
//		}
//
//
		public String getEmail() {
			return email;
		}
//
//
//		public void setEmail(String email) {
//			this.email = email;
//		}
//
//
		public String getPassword() {
			return password;
		}
//
//
		public void setPassword(String password) {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		    String usePassword = encoder.encode(password);
			this.password = usePassword;
		}
//		
//		public Boolean getIsDeleted() {
//			return isDeleted;
//		}
//
//
//		public void setIsDeleted(Boolean isDeleted) {
//			this.isDeleted = isDeleted;
//		}
//
//		public Long getCompanyId() {
//			return companyId;
//		}
//
//
//		public void setCompanyId(Long companyId) {
//			this.companyId = companyId;
//		}
//
//
//
//		public Users(Long companyId, Long userId, String firstName, String lastName, String type, String email,
//				String password, Boolean isDeleted, Company company) {
//			super();
//			this.companyId = companyId;
//			this.userId = userId;
//			this.firstName = firstName;
//			this.lastName = lastName;
//			this.type = type;
//			this.email = email;
//			this.password = password;
//			this.isDeleted = isDeleted;
//			this.company = company;
//		}
//
//
//		public Users() {
//			super();
//			
//		}


		@Override
		public String toString() {
			return "Users [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", type=" + type
					+ ", email=" + email + ", password=" + password + ", company=" + company + "]";
		}	


}
