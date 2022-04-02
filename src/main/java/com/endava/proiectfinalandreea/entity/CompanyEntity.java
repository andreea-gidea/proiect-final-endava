package com.endava.proiectfinalandreea.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@EntityListeners(AuditingEntityListener.class)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "clients_companies")
public class CompanyEntity extends Customer{


    @Column(name = "company_billing_details")
    private String companyBillingDetails;

}
