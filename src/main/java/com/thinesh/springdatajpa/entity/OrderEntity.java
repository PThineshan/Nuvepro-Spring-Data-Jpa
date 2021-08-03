package com.thinesh.springdatajpa.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "tbl_order")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer orderId;
    private Integer orderQuantity;
    private OrderStatus status;
    private Integer userId;

    @ManyToOne
    @JoinColumn(
            name = "item_id",
            referencedColumnName = "itemId"
    )
    private ItemEntity itemEntity;
}
