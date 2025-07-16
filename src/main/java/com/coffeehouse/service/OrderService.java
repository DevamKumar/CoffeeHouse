package com.coffeehouse.service;

import com.coffeehouse.dto.OrderRequest;
import com.coffeehouse.dto.OrderResponse;
import com.coffeehouse.exception.InsufficientStockException;
import com.coffeehouse.exception.ItemNotFoundException;
import com.coffeehouse.model.CoffeeItem;
import com.coffeehouse.model.Order;
import com.coffeehouse.repository.CoffeeItemRepository;
import com.coffeehouse.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final MenuService menuService;
    private final CoffeeItemRepository coffeeItemRepository;

    public OrderService(OrderRepository orderRepository, MenuService menuService, CoffeeItemRepository coffeeItemRepository) {
        this.orderRepository = orderRepository;
        this.menuService = menuService;
        this.coffeeItemRepository = coffeeItemRepository;
    }

    public OrderRequest prepareOrderRequest() {
        List<CoffeeItem> items = menuService.getAllItems();
        OrderRequest orderRequest = new OrderRequest();

        List<OrderRequest.ItemDTO> itemDTOs = items.stream()
                .map(item -> {
                    OrderRequest.ItemDTO dto = new OrderRequest.ItemDTO();
                    dto.setItemId(item.getName());
                    dto.setPrice(item.getPrice());
                    dto.setQuantity(0);
                    dto.setName(item.getName());
                    return dto;
                })
                .collect(Collectors.toList());

        orderRequest.setItems(itemDTOs);
        orderRequest.setCustomerName("");
        return orderRequest;
    }

    @Transactional
    public OrderResponse placeOrder(OrderRequest request) {
        List<OrderRequest.ItemDTO> orderedItems = request.getItems().stream()
                .filter(item -> item.getQuantity() > 0)
                .collect(Collectors.toList());

        if (orderedItems.isEmpty()) {
            throw new IllegalArgumentException("Order must contain at least one item with a quantity greater than zero.");
        }

        double total = orderedItems.stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();

        for (OrderRequest.ItemDTO orderedItem : orderedItems) {
            CoffeeItem coffeeItem = coffeeItemRepository.findById(orderedItem.getItemId())
                    .orElseThrow(() -> new ItemNotFoundException("Coffee item not found: " + orderedItem.getItemId()));

            if (coffeeItem.getQuantity() < orderedItem.getQuantity()) {
                throw new InsufficientStockException(
                        "Not enough stock for item: " + coffeeItem.getName() + ". Available: " + coffeeItem.getQuantity() + ", Ordered: " + orderedItem.getQuantity(),
                        coffeeItem.getName(),
                        coffeeItem.getQuantity()
                );
            }

            coffeeItem.setQuantity(coffeeItem.getQuantity() - orderedItem.getQuantity());
            coffeeItemRepository.save(coffeeItem);
        }

        Order order = new Order(
                request.getCustomerName(),
                orderedItems,
                total,
                LocalDateTime.now()
        );

        order = orderRepository.save(order);

        Date orderDate = Date.from(order.getOrderTime().atZone(ZoneId.systemDefault()).toInstant());

        List<OrderResponse.ItemDTO> responseItems = orderedItems.stream()
                .map(item -> new OrderResponse.ItemDTO(
                        item.getName(),
                        item.getQuantity(),
                        item.getPrice())
                )
                .collect(Collectors.toList());

        return new OrderResponse(
                order.getId(),
                order.getCustomerName(),
                responseItems,
                order.getTotalPrice(),
                orderDate
        );
    }

    public OrderResponse getOrderById(String id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        Date orderDate = Date.from(order.getOrderTime().atZone(ZoneId.systemDefault()).toInstant());

        List<OrderResponse.ItemDTO> responseItems = order.getItems().stream()
                .map(item -> new OrderResponse.ItemDTO(
                        item.getName(),
                        item.getQuantity(),
                        item.getPrice())
                )
                .collect(Collectors.toList());

        return new OrderResponse(
                order.getId(),
                order.getCustomerName(),
                responseItems,
                order.getTotalPrice(),
                orderDate
        );
    }
}
