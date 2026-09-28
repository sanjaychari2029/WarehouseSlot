package com.example.WarehouseSlot.Bin.service;

import com.example.WarehouseSlot.Bin.entity.Bin;
import com.example.WarehouseSlot.Bin.repository.BinRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BinService {

    private final BinRepository binRepository;


    public BinService(BinRepository binRepository) {
        this.binRepository = binRepository;
    }


    // CREATE BIN
    public Bin createBin(Bin bin) {

        // Validate bin code
        if (bin.getBinCode() == null ||
                bin.getBinCode().trim().isEmpty()) {

            throw new RuntimeException(
                    "Bin code is required"
            );
        }


        // Check duplicate bin code
        if (binRepository.findByBinCode(bin.getBinCode()).isPresent()) {

            throw new RuntimeException(
                    "Bin code already exists"
            );
        }


        // Validate zone
        if (bin.getZone() == null ||
                bin.getZone().trim().isEmpty()) {

            throw new RuntimeException(
                    "Zone is required"
            );
        }


        // Validate capacity
        if (bin.getCapacity() == null ||
                bin.getCapacity() <= 0) {

            throw new RuntimeException(
                    "Capacity must be greater than 0"
            );
        }


        // If occupancy is not provided, set it to 0
        if (bin.getCurrentOccupancy() == null) {

            bin.setCurrentOccupancy(0);
        }


        // Occupancy cannot be negative
        if (bin.getCurrentOccupancy() < 0) {

            throw new RuntimeException(
                    "Current occupancy cannot be negative"
            );
        }


        // Occupancy cannot exceed capacity
        if (bin.getCurrentOccupancy() >
                bin.getCapacity()) {

            throw new RuntimeException(
                    "Current occupancy cannot exceed bin capacity"
            );
        }


        // Convert zone to uppercase
        bin.setZone(
                bin.getZone().toUpperCase()
        );


        return binRepository.save(bin);
    }


    // GET ALL BINS
    public List<Bin> getAllBins() {

        return binRepository.findAll();
    }


    // GET BIN BY ID
    public Bin getBinById(Long id) {

        return binRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Bin not found with id: " + id
                        )
                );
    }


    // GET BIN BY CODE
    public Bin getBinByCode(String binCode) {

        return binRepository.findByBinCode(binCode)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Bin not found with code: " + binCode
                        )
                );
    }


    // GET BINS BY ZONE
    public List<Bin> getBinsByZone(String zone) {

        return binRepository.findByZone(
                zone.toUpperCase()
        );
    }


    // UPDATE BIN OCCUPANCY
    public Bin updateOccupancy(
            Long id,
            Integer newOccupancy) {

        Bin bin = getBinById(id);


        if (newOccupancy == null ||
                newOccupancy < 0) {

            throw new RuntimeException(
                    "Occupancy cannot be negative"
            );
        }


        if (newOccupancy > bin.getCapacity()) {

            throw new RuntimeException(
                    "Occupancy cannot exceed bin capacity"
            );
        }


        bin.setCurrentOccupancy(newOccupancy);

        return binRepository.save(bin);
    }


    // DELETE BIN
    public void deleteBin(Long id) {

        if (!binRepository.existsById(id)) {

            throw new RuntimeException(
                    "Bin not found with id: " + id
            );
        }

        binRepository.deleteById(id);
    }
}