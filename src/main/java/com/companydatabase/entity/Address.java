package com.companydatabase.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "address")
public class Address {

	    @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Long companyId;
	    private String city;
	    private String state;
	    private String country;
	    private Long pincode;
	    private Boolean isDeleted;
	    
	    @OneToOne(mappedBy = "address")
	    @JsonBackReference
	    private Company company;
	    
	    
//		public Company getCompany() {
//			return company;
////		}
//		public void setCompany(Company company) {
//			this.company = company;
//		}
//		public Long getCompanyId() {
//			return companyId;
//		}
//		public void setCompanyId(Long companyId) {
//			this.companyId = companyId;
//		}
//		public String getCity() {
//			return city;
//		}
//		public void setCity(String city) {
//			this.city = city;
//		}
//		public String getState() {
//			return state;
//		}
//		public void setState(String state) {
//			this.state = state;
//		}
//		public String getCountry() {
//			return country;
//		}
//		public void setCountry(String country) {
//			this.country = country;
//		}
//		public Long getPincode() {
//			return pincode;
//		}
//		public void setPincode(Long pincode) {
//			this.pincode = pincode;
//		}
//		
//		public Boolean getIsDeleted() {
//			return isDeleted;
//		}
//		public void setIsDeleted(Boolean isDeleted) {
//			this.isDeleted = isDeleted;
//		}
//		
//		
//		public Address(Long companyId, String city, String state, String country, Long pincode, Boolean isDeleted,
//				Company company) {
//			super();
//			this.companyId = companyId;
//			this.city = city;
//			this.state = state;
//			this.country = country;
//			this.pincode = pincode;
//			this.isDeleted = isDeleted;
//			this.company = company;
//		}
//		
//		public Address() {
//			super();
//		}
		
	   
	    
	    
}
