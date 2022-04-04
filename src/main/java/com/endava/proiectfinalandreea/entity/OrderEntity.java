package com.endava.proiectfinalandreea.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@EntityListeners(AuditingEntityListener.class)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class OrderEntity {
    @Column(name = "order_NO")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderNumber;

    @Column(name = "order_date")
    @Builder.Default
    private LocalDate orderingDate = LocalDate.now();

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status",length = 32, columnDefinition = "varchar(32) default 'NEW'")
    private OrderStatus orderStatus;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private UserEntity client;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "supplier_id", referencedColumnName = "id")
    private UserEntity supplier;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<LinesOfOrderEntity> orderLinesEntities = new ArrayList<>();

    @LastModifiedDate
    private LocalDateTime modifiedAt;
}