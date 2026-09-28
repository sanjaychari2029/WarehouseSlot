package com.example.WarehouseSlot.Zone.repository;

import com.example.WarehouseSlot.Zone.entity.Zone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ZoneRepository extends JpaRepository<Zone, Long> {

    Optional<Zone> findByZoneCode(String zoneCode);

    List<Zone> findByZoneType(String zoneType);

    List<Zone> findByZoneName(String zoneName);
}