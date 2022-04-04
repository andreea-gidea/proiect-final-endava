//package com.endava.proiectfinalandreea.entity;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.springframework.data.jpa.domain.support.AuditingEntityListener;
//
//import javax.persistence.*;
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//
//@EntityListeners(AuditingEntityListener.class)
//@Data
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
//@Entity
//@Table(name = "suppliers")
//public class SupplierEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//
//    @Column(name = "user_name")
//    private String userName;
//
//    @Column(name = "password")
//    private String password;
//
//    @Column(name = "date_account")
//    private LocalDate dateCreatedAcc=LocalDate.now();
//
//    @Column(name = "company_description")
//    private String companyDescription;
//
//    @OneToMany(
//            mappedBy = "supplier",
//            cascade = CascadeType.ALL,
//            orphanRemoval = true
//    )
//    @JsonIgnore
//    private List<OrderEntity> orders = new ArrayList<>();
//
//
//
//}
