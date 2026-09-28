package com.example.WarehouseSlot.PackList.repository;

import com.example.WarehouseSlot.PackList.entity.PackList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PackListRepository extends JpaRepository<PackList, Long> {

    Optional<PackList> findByPackListNumber(String packListNumber);

    List<PackList> findByOrderNumber(String orderNumber);

    List<PackList> findByStatus(String status);
}