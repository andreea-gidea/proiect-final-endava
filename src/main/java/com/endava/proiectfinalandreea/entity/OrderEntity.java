package com.endava.proiectfinalandreea.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
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

    @Column(name = "delivery_date")
    @Builder.Default
    private LocalDate shippingDate = LocalDate.now().plusDays(1);

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private ClientEntity client;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "supplier_id", referencedColumnName = "id")
    private SupplierEntity supplier;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<LinesOfOrderEntity> orderLinesEntities = new ArrayList<>();

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;

}