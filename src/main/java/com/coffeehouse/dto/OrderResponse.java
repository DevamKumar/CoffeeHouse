package com.coffeehouse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    private String orderId;
    private String customerName;
    private List<ItemDTO> items;
    private double totalPrice;
    private Date orderTime;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ItemDTO {
        private String name;
        private int quantity;
        private double price;
    }
}
