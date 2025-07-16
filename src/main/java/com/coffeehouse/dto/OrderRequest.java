package com.coffeehouse.dto;

import lombok.Data;
import java.util.List;

@Data
public class OrderRequest {
    private String customerName;
    private List<ItemDTO> items;

    @Data
    public static class ItemDTO {
        private String itemId;
        private double price;
        private int quantity;
    }
}
