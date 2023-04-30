package com.companydatabase.entity;

import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "registration")
public class Registration {
    
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long companyId;
	
    @Column(name = "registration_id")
    private String registrationId;

    @Column(name="id")
    private Long id;
    
    @Column(name = "legal_name")
    private String legalName;

    @DateTimeFormat(iso = ISO.DATE)
    @Column(name = "dor")
    private LocalDate DOR;
    
    @Column(name="isDeleted")
    private Boolean isDeleted;
   

    @OneToOne(mappedBy = "registration")
    @JsonBackReference
    private Company company;
    
//		public String getRegistrationId() {
//			return registrationId;
//		}
//
//
//		public Company getCompany() {
//			return company;
//		}
//
//		public void setCompany(Company company) {
//			this.company = company;
//		}
//
//		public void setRegistrationId(String registrationId) {
//			this.registrationId = registrationId;
//		}
//
//		public String getLegalName() {
//			return legalName;
//		}
//
//		public void setLegalName(String legalName) {
//			this.legalName = legalName;
//		}
//
//		public LocalDate getDOR() {
//			return DOR;
//		}
//
//		public void setDOR(LocalDate dOR) {
//			this.DOR = dOR;
//		}
//		public Long getId() {
//			return id;
//		}
//
//		public void setId(Long id) {
//			this.id = id;
//		}
//		
//
//		public Boolean getIsDeleted() {
//			return isDeleted;
//		}
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
//		public Registration(Long companyId, String registrationId, Long id, String legalName, LocalDate dOR,
//				Boolean isDeleted, Company company) {
//			super();
//			this.companyId = companyId;
//			this.registrationId = registrationId;
//			this.id = id;
//			this.legalName = legalName;
//			this.DOR = dOR;
//			this.isDeleted = isDeleted;
//			this.company = company;
//		}
//
//
//		public Registration() {
//			super();
//		}
		
	    
	    
	}
