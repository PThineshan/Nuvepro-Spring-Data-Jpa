package com.thinesh.springdatajpa.controller;

import com.thinesh.springdatajpa.entity.ItemEntity;
import com.thinesh.springdatajpa.service.ItemService;
import io.swagger.annotations.ApiOperation;
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
    @ApiOperation(value = "FindAllItems", notes = "Get all the items from item table")
    public List<ItemEntity> getAllItems(){
        return itemService.getAllItems();
    }
}
