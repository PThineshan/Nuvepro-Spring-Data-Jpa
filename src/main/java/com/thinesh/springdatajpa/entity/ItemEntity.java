package com.thinesh.springdatajpa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "tbl_item")
public class ItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer itemId;
    @Column(nullable = false)
    private String itemName;
    @Column(nullable = false)
    private Integer itemQuantity;
    @Column(nullable = false)
    private Double itemPrice;
}
