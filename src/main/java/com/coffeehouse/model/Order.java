package com.coffeehouse.model;

import com.coffeehouse.dto.OrderRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "orders")
public class Order {

    @Id
    private String id;

    private String customerName;
    private List<OrderRequest.ItemDTO> items;
    private double totalPrice;
    private LocalDateTime orderTime;


    public Order(String customerName, List<OrderRequest.ItemDTO> items, double totalPrice, LocalDateTime orderTime) {
        this.customerName = customerName;
        this.items = items;
        this.totalPrice = totalPrice;
        this.orderTime = orderTime;
    }


}