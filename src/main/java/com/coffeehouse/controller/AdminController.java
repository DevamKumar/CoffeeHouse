package com.coffeehouse.controller;


import com.coffeehouse.dto.AdminDashboardResponse;
import com.coffeehouse.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/admin")
    public String getAdminDashboard(Model model){
        AdminDashboardResponse response = adminService.getDashboardSummary();
        model.addAttribute("dashboard", response);
        return "admin";
    }
}
