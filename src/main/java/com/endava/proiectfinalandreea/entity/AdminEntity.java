package com.endava.proiectfinalandreea.entity;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@EntityListeners(AuditingEntityListener.class)
@Entity
@Data
@Table(name = "admins")
public class AdminEntity extends User{



}
