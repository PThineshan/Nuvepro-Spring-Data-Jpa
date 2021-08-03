package com.thinesh.springdatajpa.entity;

import lombok.Data;

@Data
public class OrderRequest {
    private Integer itemId;
    private Integer itemQuantity;
    private Integer userId;
}
