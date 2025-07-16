package com.coffeehouse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class AdminDashboardResponse {
    private double totalRevenue;
    private List<TopSellingItem> topSellingItems;
    private List<DailyOrderSummary> dailyOrderSummaries;
}