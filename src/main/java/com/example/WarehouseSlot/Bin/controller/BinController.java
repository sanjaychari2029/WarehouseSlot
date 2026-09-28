package com.example.WarehouseSlot.Bin.controller;

import com.example.WarehouseSlot.Bin.entity.Bin;
import com.example.WarehouseSlot.Bin.service.BinService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bins")
public class BinController {

    private final BinService binService;


    public BinController(BinService binService) {
        this.binService = binService;
    }


    // CREATE BIN
    @PostMapping
    public ResponseEntity<Bin> createBin(
            @RequestBody Bin bin) {

        Bin savedBin = binService.createBin(bin);

        return new ResponseEntity<>(
                savedBin,
                HttpStatus.CREATED
        );
    }


    // GET ALL BINS
    @GetMapping
    public ResponseEntity<List<Bin>> getAllBins() {

        return ResponseEntity.ok(
                binService.getAllBins()
        );
    }


    // GET BIN BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Bin> getBinById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                binService.getBinById(id)
        );
    }


    // GET BIN BY CODE
    @GetMapping("/code/{binCode}")
    public ResponseEntity<Bin> getBinByCode(
            @PathVariable String binCode) {

        return ResponseEntity.ok(
                binService.getBinByCode(binCode)
        );
    }


    // GET BINS BY ZONE
    @GetMapping("/zone/{zone}")
    public ResponseEntity<List<Bin>> getBinsByZone(
            @PathVariable String zone) {

        return ResponseEntity.ok(
                binService.getBinsByZone(zone)
        );
    }


    // UPDATE OCCUPANCY
    @PutMapping("/{id}/occupancy")
    public ResponseEntity<Bin> updateOccupancy(
            @PathVariable Long id,
            @RequestParam Integer quantity) {

        return ResponseEntity.ok(
                binService.updateOccupancy(
                        id,
                        quantity
                )
        );
    }


    // DELETE BIN
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBin(
            @PathVariable Long id) {

        binService.deleteBin(id);

        return ResponseEntity.ok(
                "Bin deleted successfully"
        );
    }
}