package com.coffeehouse.controller;

import com.coffeehouse.dto.OrderRequest;
import com.coffeehouse.dto.OrderResponse;
import com.coffeehouse.exception.InsufficientStockException;
import com.coffeehouse.service.MenuService;
import com.coffeehouse.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService, MenuService menuService) {
        this.orderService = orderService;
    }

    @GetMapping("/order")
    public String showOrderForm(Model model) {
        OrderRequest orderRequest = orderService.prepareOrderRequest();
        model.addAttribute("orderRequest", orderRequest);

        if (model.asMap().containsKey("errorMessage")) {
            model.addAttribute("errorMessage", model.asMap().get("errorMessage"));
        }
        if (model.asMap().containsKey("errorItemName")) {
            model.addAttribute("errorItemName", model.asMap().get("errorItemName"));
        }
        if (model.asMap().containsKey("errorStockLeft")) {
            model.addAttribute("errorStockLeft", model.asMap().get("errorStockLeft"));
        }

        return "order";
    }

    @PostMapping("/order/submit")
    public String placeOrder(@ModelAttribute OrderRequest request, Model model, RedirectAttributes redirectAttributes) {
        try {
            OrderResponse response = orderService.placeOrder(request);
            model.addAttribute("response", response);
            return "confirmation";
        } catch (InsufficientStockException ex) {
            // FIX: Add error details to flash attributes and redirect to order page
            redirectAttributes.addFlashAttribute("errorMessage", ex.getMessage());
            redirectAttributes.addFlashAttribute("errorItemName", ex.getItemName());
            redirectAttributes.addFlashAttribute("errorStockLeft", ex.getStockLeft());
            return "redirect:/order"; // Redirect back to the order page
        } catch (IllegalArgumentException ex) {
            // Handle other IllegalArgumentExceptions (e.g., empty order)
            redirectAttributes.addFlashAttribute("errorMessage", ex.getMessage());
            return "redirect:/order";
        } catch (Exception ex) {
            // Generic error handling
            redirectAttributes.addFlashAttribute("errorMessage", "An unexpected error occurred: " + ex.getMessage());
            return "redirect:/order";
        }
    }
}
