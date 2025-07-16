package com.coffeehouse.service;

import com.coffeehouse.dto.OrderRequest;
import com.coffeehouse.dto.OrderResponse;
import com.coffeehouse.model.Order;
import com.coffeehouse.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public OrderResponse placeOrder(OrderRequest request) {
        double total = request.getItems().stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();

        Order order = new Order(
                request.getCustomerName(),
                request.getItems(),
                total,
                LocalDateTime.now()
        );

        order = orderRepository.save(order);

        Date orderDate = Date.from(order.getOrderTime().atZone(ZoneId.systemDefault()).toInstant());

        List<OrderResponse.ItemDTO> responseItems = request.getItems().stream()
                .map(item -> new OrderResponse.ItemDTO(
                        item.getItemId(),
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
                        item.getItemId(),
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
