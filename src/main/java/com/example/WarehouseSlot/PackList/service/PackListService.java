package com.example.WarehouseSlot.PackList.Service;

import com.example.WarehouseSlot.PackList.entity.PackList;
import com.example.WarehouseSlot.PackList.repository.PackListRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PackListService {

    private final PackListRepository repository;

    public PackListService(PackListRepository repository) {
        this.repository = repository;
    }

    // Create Pack List
    public PackList createPackList(PackList packList) {

        if (packList.getPackListNumber() == null ||
                packList.getPackListNumber().trim().isEmpty()) {
            throw new RuntimeException("Pack list number is required");
        }

        if (packList.getOrderNumber() == null ||
                packList.getOrderNumber().trim().isEmpty()) {
            throw new RuntimeException("Order number is required");
        }

        if (packList.getItemName() == null ||
                packList.getItemName().trim().isEmpty()) {
            throw new RuntimeException("Item name is required");
        }

        if (packList.getQuantity() == null ||
                packList.getQuantity() <= 0) {
            throw new RuntimeException("Quantity must be greater than 0");
        }

        if (repository.findByPackListNumber(
                packList.getPackListNumber()).isPresent()) {
            throw new RuntimeException("Pack list number already exists");
        }

        if (packList.getStatus() == null ||
                packList.getStatus().trim().isEmpty()) {
            packList.setStatus("PENDING");
        }

        packList.setStatus(packList.getStatus().toUpperCase());

        validateStatus(packList.getStatus());

        return repository.save(packList);
    }

    // Get all Pack Lists
    public List<PackList> getAllPackLists() {
        return repository.findAll();
    }

    // Get Pack List by ID
    public PackList getPackListById(Long id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Pack list not found"));
    }

    // Get by Pack List Number
    public PackList getByPackListNumber(String packListNumber) {

        return repository.findByPackListNumber(packListNumber)
                .orElseThrow(() ->
                        new RuntimeException("Pack list not found"));
    }

    // Get by Order Number
    public List<PackList> getByOrderNumber(String orderNumber) {
        return repository.findByOrderNumber(orderNumber);
    }

    // Get by Status
    public List<PackList> getByStatus(String status) {
        return repository.findByStatus(status.toUpperCase());
    }

    // Update Pack List
    public PackList updatePackList(Long id, PackList updated) {

        PackList existing = getPackListById(id);

        if (updated.getOrderNumber() != null) {
            existing.setOrderNumber(updated.getOrderNumber());
        }

        if (updated.getItemName() != null) {
            existing.setItemName(updated.getItemName());
        }

        if (updated.getQuantity() != null) {

            if (updated.getQuantity() <= 0) {
                throw new RuntimeException(
                        "Quantity must be greater than 0");
            }

            existing.setQuantity(updated.getQuantity());
        }

        if (updated.getStatus() != null) {

            String status = updated.getStatus().toUpperCase();

            validateStatus(status);

            existing.setStatus(status);
        }

        return repository.save(existing);
    }

    // Update Status only
    public PackList updateStatus(Long id, String status) {

        PackList packList = getPackListById(id);

        status = status.toUpperCase();

        validateStatus(status);

        packList.setStatus(status);

        return repository.save(packList);
    }

    // Delete Pack List
    public void deletePackList(Long id) {

        if (!repository.existsById(id)) {
            throw new RuntimeException("Pack list not found");
        }

        repository.deleteById(id);
    }

    // Status validation
    private void validateStatus(String status) {

        if (!status.equals("PENDING") &&
                !status.equals("PACKING") &&
                !status.equals("PACKED")) {

            throw new RuntimeException(
                    "Status must be PENDING, PACKING or PACKED");
        }
    }
}