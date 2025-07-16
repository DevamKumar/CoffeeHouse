package com.coffeehouse.exception;

import lombok.Getter;

@Getter
public class InsufficientStockException extends RuntimeException {
    private final String itemName;
    private final int stockLeft;

    public InsufficientStockException(String message, String itemName, int stockLeft) {
        super(message);
        this.itemName = itemName;
        this.stockLeft = stockLeft;
    }
}
