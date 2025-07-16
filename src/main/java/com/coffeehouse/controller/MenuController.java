package com.coffeehouse.controller;

import com.coffeehouse.model.CoffeeItem;
import com.coffeehouse.service.MenuService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class MenuController {

    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/menu")
    public String getMenu(Model model) {
        List<CoffeeItem> menu = menuService.getAllItems();
        model.addAttribute("menu", menu);
        return "menu";
    }

    @PostMapping("/menu")
    public String addItem(@ModelAttribute CoffeeItem item, Model model) {
        menuService.addItem(item);
        return "redirect:/menu";
    }
}
