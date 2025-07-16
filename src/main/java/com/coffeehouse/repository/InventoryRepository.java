package com.coffeehouse.repository;

import com.coffeehouse.model.InventoryItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends MongoRepository<InventoryItem, String> {
}


