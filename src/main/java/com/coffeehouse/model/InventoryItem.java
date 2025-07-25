package com.coffeehouse.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "inventory")

public class InventoryItem {

    @Id
    private String id;

    private String itemName;
    private int stockQuantity;
}
