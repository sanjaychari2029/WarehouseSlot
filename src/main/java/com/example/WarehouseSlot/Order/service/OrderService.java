package com.example.WarehouseSlot.Order.service;

import com.example.WarehouseSlot.Order.entity.Order;
import com.example.WarehouseSlot.Order.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    // Create Order
    public Order createOrder(Order order) {

        if (order.getOrderNumber() == null ||
                order.getOrderNumber().isBlank()) {
            throw new RuntimeException("Order number is required");
        }

        if (order.getItemName() == null ||
                order.getItemName().isBlank()) {
            throw new RuntimeException("Item name is required");
        }

        if (order.getQuantity() == null ||
                order.getQuantity() <= 0) {
            throw new RuntimeException("Quantity must be greater than 0");
        }

        if (orderRepository
                .findByOrderNumber(order.getOrderNumber())
                .isPresent()) {

            throw new RuntimeException("Order number already exists");
        }

        order.setStatus("PENDING");

        return orderRepository.save(order);
    }

    // Get all orders
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // Get order by ID
    public Order getOrderById(Long id) {

        return orderRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Order not found: " + id
                        ));
    }

    // Get orders by status
    public List<Order> getOrdersByStatus(String status) {

        return orderRepository.findByStatus(
                status.toUpperCase()
        );
    }

    // Update status
    public Order updateStatus(Long id, String status) {

        Order order = getOrderById(id);

        status = status.toUpperCase();

        if (!status.equals("PENDING") &&
                !status.equals("PICKED") &&
                !status.equals("COMPLETED")) {

            throw new RuntimeException(
                    "Status must be PENDING, PICKED or COMPLETED"
            );
        }

        order.setStatus(status);

        return orderRepository.save(order);
    }

    // Delete order
    public void deleteOrder(Long id) {

        if (!orderRepository.existsById(id)) {
            throw new RuntimeException("Order not found");
        }

        orderRepository.deleteById(id);
    }
}