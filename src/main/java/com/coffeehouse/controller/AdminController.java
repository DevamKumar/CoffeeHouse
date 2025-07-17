package com.coffeehouse.controller;


import com.coffeehouse.dto.AdminDashboardResponse;
import com.coffeehouse.model.CoffeeItem;
import com.coffeehouse.service.AdminService;
import com.coffeehouse.service.MenuService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class AdminController {

    private final AdminService adminService;
    private final MenuService menuService;

    public AdminController(AdminService adminService, MenuService menuService) {
        this.adminService = adminService;
        this.menuService = menuService;
    }

    @GetMapping("/admin")
    public String getAdminDashboard(Model model){
        AdminDashboardResponse response = adminService.getDashboardSummary();
        model.addAttribute("dashboard", response);
        model.addAttribute("coffeeItem", new CoffeeItem());
        model.addAttribute("menuItems", menuService.getAllItems());

        return "admin";
    }

    @PostMapping("/admin/add-coffee-item")
    public String addCoffeeItem(@ModelAttribute CoffeeItem item, Model model){
        menuService.addItem(item);
        return "redirect:/admin";
    }

    @PostMapping("/admin/delete-coffee-item")
    public String deleteCoffeeItem(@RequestParam("itemName") String itemName){
        menuService.deleteItem(itemName);
        return "redirect:/admin";
    }


}
