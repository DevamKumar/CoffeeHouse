package com.coffeehouse.repository.custom;

import com.coffeehouse.dto.DailyOrderSummary;
import com.coffeehouse.dto.TopSellingItem;

import java.util.List;

public interface CustomOrderRepository{
    double calculateTotalRevenue();
    List<TopSellingItem> getTopSellingItems();
    List<DailyOrderSummary> getDailyOrderSummary();
}

