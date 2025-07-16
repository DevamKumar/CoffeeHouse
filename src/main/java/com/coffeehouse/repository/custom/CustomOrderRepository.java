package com.coffeehouse.repository.custom;

import com.coffeehouse.dto.DailyOrderSummary;

import java.util.List;

public interface CustomOrderRepository{
    double calculateTotalRevenue();
    List<DailyOrderSummary> getDailyOrderSummary();
}

