package com.coffeehouse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class DailyOrderSummary {
    private String orderDate;
    private int totalOrder;
    private double totalRevenue;
}