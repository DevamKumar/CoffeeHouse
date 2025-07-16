package com.coffeehouse.repository;

import com.coffeehouse.model.Order;
import com.coffeehouse.repository.custom.CustomOrderRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends MongoRepository<Order, String>, CustomOrderRepository {
}


