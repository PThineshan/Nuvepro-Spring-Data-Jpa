package com.thinesh.springdatajpa.repository;

import com.thinesh.springdatajpa.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {

    @Query("select o from OrderEntity o where o.itemEntity.itemId=:itemId")
    List<OrderEntity> getAllOrderByItemId(@Param("itemId") Integer itemId);
    List<OrderEntity> findByUserId(Integer userId);
}
