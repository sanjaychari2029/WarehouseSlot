package com.example.WarehouseSlot.PackList.controller;

import com.example.WarehouseSlot.PackList.entity.PackList;
import com.example.WarehouseSlot.PackList.Service.PackListService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/packlists")
public class PackListController {

    private final PackListService service;

    public PackListController(PackListService service) {
        this.service = service;
    }

    // Create Pack List
    @PostMapping
    public PackList createPackList(@RequestBody PackList packList) {
        return service.createPackList(packList);
    }

    // Get all Pack Lists
    @GetMapping
    public List<PackList> getAllPackLists() {
        return service.getAllPackLists();
    }

    // Get Pack List by ID
    @GetMapping("/{id}")
    public PackList getPackListById(@PathVariable Long id) {
        return service.getPackListById(id);
    }

    // Get by Pack List Number
    @GetMapping("/number/{packListNumber}")
    public PackList getByPackListNumber(
            @PathVariable String packListNumber) {

        return service.getByPackListNumber(packListNumber);
    }

    // Get by Order Number
    @GetMapping("/order/{orderNumber}")
    public List<PackList> getByOrderNumber(
            @PathVariable String orderNumber) {

        return service.getByOrderNumber(orderNumber);
    }

    // Get by Status
    @GetMapping("/status/{status}")
    public List<PackList> getByStatus(
            @PathVariable String status) {

        return service.getByStatus(status);
    }

    // Update Pack List
    @PutMapping("/{id}")
    public PackList updatePackList(
            @PathVariable Long id,
            @RequestBody PackList packList) {

        return service.updatePackList(id, packList);
    }

    // Update Status
    @PutMapping("/{id}/status")
    public PackList updateStatus(
            @PathVariable Long id,
            @RequestParam String status) {

        return service.updateStatus(id, status);
    }

    // Delete Pack List
    @DeleteMapping("/{id}")
    public String deletePackList(@PathVariable Long id) {

        service.deletePackList(id);

        return "Pack list deleted successfully";
    }
}