package com.thinesh.springdatajpa.service;

import com.thinesh.springdatajpa.entity.ItemEntity;
import com.thinesh.springdatajpa.entity.OrderEntity;
import com.thinesh.springdatajpa.entity.OrderRequest;
import com.thinesh.springdatajpa.entity.OrderStatus;
import com.thinesh.springdatajpa.exception.ItemNotFoundException;
import com.thinesh.springdatajpa.exception.ItemQuantityNotEnoughException;
import com.thinesh.springdatajpa.exception.OrderIsBlockedException;
import com.thinesh.springdatajpa.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;


@Service
public class OrderService {
    @Autowired
    private ItemService itemService;
    @Autowired
    private OrderRepository orderRepository;

    public OrderEntity createOrder(OrderRequest orderRequest){
        ItemEntity itemEntity = itemService.getItemById(orderRequest.getItemId());
        if (itemEntity == null)
            return null;

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setStatus(OrderStatus.ORDER_PLACED);
        orderEntity.setItemEntity(itemEntity);
        orderEntity.setOrderQuantity(orderRequest.getItemQuantity());
        orderEntity.setUserId(orderRequest.getUserId());

        orderRepository.save(orderEntity);
        return orderEntity;
    }

    public OrderEntity createOrderWithQuantity(OrderRequest orderRequest){
        ItemEntity itemEntity = itemService.getItemById(orderRequest.getItemId());
        if (itemEntity == null){
            throw new ItemNotFoundException();
        }
        if(itemEntity.getItemQuantity()<orderRequest.getItemQuantity()){
            throw new ItemQuantityNotEnoughException();
        }

        List<OrderEntity> orderEntityList = orderRepository.getAllOrderByItemId(orderRequest.getItemId());
        int alreadyOrderedPlacedTotal=0;
        if(orderEntityList!=null){
            for(OrderEntity orderEntity: orderEntityList){
                if(orderEntity.getStatus() == OrderStatus.ORDER_PLACED){
                    alreadyOrderedPlacedTotal += orderEntity.getOrderQuantity();
                }else if(orderEntity.getStatus() == OrderStatus.ORDER_REJECTED){
                    alreadyOrderedPlacedTotal -= orderEntity.getOrderQuantity();
                }
            }
        }
        
        if(itemEntity.getItemQuantity()-alreadyOrderedPlacedTotal<orderRequest.getItemQuantity()){
            throw new OrderIsBlockedException();
        }

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setStatus(OrderStatus.ORDER_PLACED);
        orderEntity.setItemEntity(itemEntity);
        orderEntity.setOrderQuantity(orderRequest.getItemQuantity());
        orderEntity.setUserId(orderRequest.getUserId());

        orderRepository.save(orderEntity);
        return orderEntity;
    }
    public List<OrderEntity> getAllOrders(){
        return orderRepository.findAll();
    }

    public List<OrderEntity> getAllOrdersByUserId(Integer userId){
        return orderRepository.findByUserId(userId);
    }



    public void updateOrderStatus(Integer orderId, OrderStatus status){
        Optional<OrderEntity> orderEntity = orderRepository.findById(orderId);
        if (orderEntity.isPresent()){
            orderEntity.get().setStatus(status);
            orderRepository.save(orderEntity.get());
        }
    }
}
