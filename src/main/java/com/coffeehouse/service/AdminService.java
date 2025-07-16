package com.coffeehouse.service;

import com.coffeehouse.dto.AdminDashboardResponse;
import com.coffeehouse.dto.DailyOrderSummary;
import com.coffeehouse.dto.TopSellingItem;
import com.coffeehouse.repository.custom.CustomOrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    private final CustomOrderRepository customOrderRepository;

    public AdminService(CustomOrderRepository customOrderRepository) {
        this.customOrderRepository = customOrderRepository;
    }

    public AdminDashboardResponse getDashboardSummary(){
        double totalRevenue = customOrderRepository.calculateTotalRevenue();
        List<TopSellingItem> topItems = customOrderRepository.getTopSellingItems();
        List<DailyOrderSummary> dailySummary = customOrderRepository.getDailyOrderSummary();

        return new AdminDashboardResponse(totalRevenue, topItems, dailySummary);
    }
}
