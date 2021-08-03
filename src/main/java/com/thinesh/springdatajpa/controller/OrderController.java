package com.thinesh.springdatajpa.controller;

import com.thinesh.springdatajpa.entity.OrderEntity;
import com.thinesh.springdatajpa.entity.OrderRequest;
import com.thinesh.springdatajpa.entity.OrderStatus;
import com.thinesh.springdatajpa.exception.ItemNotFoundException;
import com.thinesh.springdatajpa.exception.ItemQuantityNotEnoughException;
import com.thinesh.springdatajpa.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<?> placeorder(@RequestBody OrderRequest orderRequest){
        try{
            OrderEntity orderEntity = orderService.createOrder(orderRequest);
            if (orderEntity == null)
                return new ResponseEntity<>("Unable to place the order", HttpStatus.BAD_REQUEST);
            else
                return new ResponseEntity<>("Order placed", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Unable to place the order", HttpStatus.BAD_REQUEST);
        }
    }

    /**Challenge 1*/
    @PostMapping("/createOrderWithQuantity")
    public ResponseEntity<?> placeorderWithEnoughQuantity(@RequestBody OrderRequest orderRequest) throws ItemQuantityNotEnoughException, ItemNotFoundException {
        OrderEntity orderEntity = orderService.createOrderWithQuantity(orderRequest);
        return new ResponseEntity<>("Order placed", HttpStatus.OK);

    }

    @GetMapping("/getAllOrders")
    public List<OrderEntity> getAllOrders(){
        try {
            return orderService.getAllOrders();
        }catch (Exception e){
            return null;
        }
    }

    /**Challenge 2 */
    @GetMapping("/getAllOrders/{userId}")
    public List<OrderEntity> getAllOrdersByUserId(@PathVariable Integer userId) {
        try {
            return orderService.getAllOrdersByUserId(userId);
        } catch (Exception e) {
            return null;
        }
    }

    @PutMapping("update/{orderId}/{status}")
    public void updateOrderStatus(@PathVariable Integer orderId, @PathVariable OrderStatus status){
        try {
            orderService.updateOrderStatus(orderId, status);
        }catch (Exception e){
        }
    }
}
