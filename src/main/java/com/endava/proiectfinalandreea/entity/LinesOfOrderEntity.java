package com.endava.proiectfinalandreea.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;


@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "lines_of_order")
public class LinesOfOrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nr_crt")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private OrderEntity order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @Column(name = "number_of_products")
    private Integer numberOfProducts;

    public LinesOfOrderEntity() {
    }

    public LinesOfOrderEntity(OrderEntity orderEntity, ProductEntity productEntity, Integer numberOfProducts) {
        this.order = orderEntity;
        this.product = productEntity;
        this.numberOfProducts = numberOfProducts;
    }

}
