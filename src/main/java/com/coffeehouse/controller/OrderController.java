package com.coffeehouse.controller;


import com.coffeehouse.dto.OrderRequest;
import com.coffeehouse.dto.OrderResponse;
import com.coffeehouse.model.CoffeeItem;
import com.coffeehouse.service.MenuService;
import com.coffeehouse.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class OrderController {

    private final OrderService orderService;
    private final MenuService menuService;

    public OrderController(OrderService orderService, MenuService menuService) {
        this.orderService = orderService;
        this.menuService = menuService;
    }

    @GetMapping("/order")
    public String showOrderForm(Model model) {
        List<CoffeeItem> items = menuService.getAllItems();
        model.addAttribute("items", items);
        model.addAttribute("orderRequest", new OrderRequest());
        return "order";
    }

    @PostMapping("/order/submit")
    public String placeOrder(@ModelAttribute OrderRequest request, Model model) {
        OrderResponse response = orderService.placeOrder(request);
        model.addAttribute("response", response);
        return "confirmation";
    }
}
