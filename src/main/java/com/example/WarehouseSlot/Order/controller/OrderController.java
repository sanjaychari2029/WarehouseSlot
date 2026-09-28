package com.example.WarehouseSlot.Order.controller;

import com.example.WarehouseSlot.Order.entity.Order;
import com.example.WarehouseSlot.Order.service.OrderService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // Create Order
    @PostMapping
    public ResponseEntity<Order> createOrder(
            @RequestBody Order order) {

        return new ResponseEntity<>(
                orderService.createOrder(order),
                HttpStatus.CREATED
        );
    }

    // Get all Orders
    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {

        return ResponseEntity.ok(
                orderService.getAllOrders()
        );
    }

    // Get Order by ID
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                orderService.getOrderById(id)
        );
    }

    // Get Orders by status
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Order>> getOrdersByStatus(
            @PathVariable String status) {

        return ResponseEntity.ok(
                orderService.getOrdersByStatus(status)
        );
    }

    // Update status
    @PutMapping("/{id}/status")
    public ResponseEntity<Order> updateStatus(
            @PathVariable Long id,
            @RequestParam String status) {

        return ResponseEntity.ok(
                orderService.updateStatus(id, status)
        );
    }

    // Delete Order
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(
            @PathVariable Long id) {

        orderService.deleteOrder(id);

        return ResponseEntity.ok(
                "Order deleted successfully"
        );
    }
}