package com.thinesh.springdatajpa.controller;

import com.thinesh.springdatajpa.entity.ItemEntity;
import com.thinesh.springdatajpa.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @PostMapping("/create")
    public ItemEntity createItem(@RequestBody ItemEntity itemEntity){
        return itemService.createItem(itemEntity);
    }

    @GetMapping("/getAllItems")
    public List<ItemEntity> getAllItems(){
        return itemService.getAllItems();
    }
}
