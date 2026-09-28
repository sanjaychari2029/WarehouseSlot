package com.example.WarehouseSlot.PackList.controller;

import com.example.WarehouseSlot.PackList.entity.PackList;
import com.example.WarehouseSlot.PackList.Service.PackListService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/packlists")
public class PackListController {

    private final PackListService packListService;

    public PackListController(
            PackListService packListService) {

        this.packListService = packListService;
    }


    // CREATE PACK LIST
    @PostMapping
    public ResponseEntity<PackList> createPackList(
            @RequestBody PackList packList) {

        return new ResponseEntity<>(
                packListService.createPackList(PackList),
                HttpStatus.CREATED
        );
    }


    // GET ALL PACK LISTS
    @GetMapping
    public ResponseEntity<List<PackList>> getAllPackLists() {

        return ResponseEntity.ok(
                packListService.getAllPackLists()
        );
    }


    // GET PACK LIST BY ID
    @GetMapping("/{id}")
    public ResponseEntity<PackList> getPackListById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                packListService.getPackListById(id)
        );
    }


    // GET PACK LISTS BY ORDER NUMBER
    @GetMapping("/order/{orderNumber}")
    public ResponseEntity<List<PackList>> getByOrder(
            @PathVariable String orderNumber) {

        return ResponseEntity.ok(
                packListService.getPackListsByOrder(
                        orderNumber
                )
        );
    }


    // GET PACK LISTS BY STATUS
    @GetMapping("/status/{status}")
    public ResponseEntity<List<PackList>> getByStatus(
            @PathVariable String status) {

        return ResponseEntity.ok(
                packListService.getPackListsByStatus(
                        status
                )
        );
    }


    // UPDATE STATUS
    @PutMapping("/{id}/status")
    public ResponseEntity<PackList> updateStatus(
            @PathVariable Long id,
            @RequestParam String status) {

        return ResponseEntity.ok(
                packListService.updateStatus(
                        id,
                        status
                )
        );
    }


    // DELETE PACK LIST
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePackList(
            @PathVariable Long id) {

        packListService.deletePackList(id);

        return ResponseEntity.ok(
                "Pack list deleted successfully"
        );
    }
}