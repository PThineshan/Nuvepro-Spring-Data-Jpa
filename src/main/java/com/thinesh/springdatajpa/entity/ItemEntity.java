package com.thinesh.springdatajpa.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "tbl_item")
@ApiModel(description = "Details about ItemEntity")
public class ItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes = "Unique id of the item")
    private Integer itemId;
    @Column(nullable = false)
    @ApiModelProperty(notes = "Name of the item")
    private String itemName;
    @Column(nullable = false)
    @ApiModelProperty(notes = "Quantity of the item")
    private Integer itemQuantity;
    @Column(nullable = false)
    @ApiModelProperty(notes = "Price of the item")
    private Double itemPrice;
}
