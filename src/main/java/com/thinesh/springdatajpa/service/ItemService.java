package com.thinesh.springdatajpa.service;

import com.thinesh.springdatajpa.entity.ItemEntity;
import com.thinesh.springdatajpa.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;

    public ItemEntity createItem(ItemEntity itemEntity){
        return itemRepository.save(itemEntity);
    }

    public List<ItemEntity> getAllItems(){
        return itemRepository.findAll();
    }

    public ItemEntity getItemById(Integer itemId){
        Optional<ItemEntity> entity = itemRepository.findById(itemId);

        if (entity.isPresent())
            return entity.get();
        else
            return null;
    }
}
