package com.coffeehouse.service;


import com.coffeehouse.model.CoffeeItem;
import com.coffeehouse.repository.CoffeeItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {

    private final CoffeeItemRepository coffeeItemRepository;

    public MenuService(CoffeeItemRepository coffeeItemRepository) {
        this.coffeeItemRepository = coffeeItemRepository;
    }


    public List<CoffeeItem> getAllItems() {
        return coffeeItemRepository.findAll();
    }

    public CoffeeItem addItem(CoffeeItem item) {
        return coffeeItemRepository.save(item);
    }

}
