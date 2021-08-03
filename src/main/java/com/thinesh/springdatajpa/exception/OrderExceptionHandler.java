package com.thinesh.springdatajpa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class OrderExceptionHandler {
        @ExceptionHandler(value = ItemQuantityNotEnoughException.class)
        public ResponseEntity<Object> exception(ItemQuantityNotEnoughException exception) {
            return new ResponseEntity<>("ItemQuantity not enough", HttpStatus.NOT_ACCEPTABLE);
        }

        @ExceptionHandler(value = ItemNotFoundException.class)
        public ResponseEntity<Object> exception(ItemNotFoundException exception) {
            return new ResponseEntity<>("Item not found", HttpStatus.NOT_FOUND);
        }

        @ExceptionHandler(value = OrderIsBlockedException.class)
        public ResponseEntity<Object> exception(OrderIsBlockedException exception) {
            return new ResponseEntity<>("Order is blocked", HttpStatus.NOT_FOUND);
        }


}
