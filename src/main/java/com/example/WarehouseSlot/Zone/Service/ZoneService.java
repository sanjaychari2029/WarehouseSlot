package com.example.WarehouseSlot.Zone.Service;

import com.example.WarehouseSlot.Zone.entity.Zone;
import com.example.WarehouseSlot.Zone.repository.ZoneRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZoneService {

    private final ZoneRepository repository;

    public ZoneService(ZoneRepository repository) {
        this.repository = repository;
    }

    // Create Zone
    public Zone createZone(Zone zone) {

        if (zone.getZoneCode() == null ||
                zone.getZoneCode().trim().isEmpty()) {
            throw new RuntimeException("Zone code is required");
        }

        if (zone.getZoneName() == null ||
                zone.getZoneName().trim().isEmpty()) {
            throw new RuntimeException("Zone name is required");
        }

        if (zone.getZoneType() == null ||
                zone.getZoneType().trim().isEmpty()) {
            throw new RuntimeException("Zone type is required");
        }

        if (zone.getCapacity() == null ||
                zone.getCapacity() <= 0) {
            throw new RuntimeException(
                    "Capacity must be greater than 0");
        }

        if (zone.getCurrentOccupancy() == null) {
            zone.setCurrentOccupancy(0);
        }

        if (zone.getCurrentOccupancy() < 0 ||
                zone.getCurrentOccupancy() > zone.getCapacity()) {
            throw new RuntimeException(
                    "Current occupancy must be between 0 and capacity");
        }

        if (repository.findByZoneCode(zone.getZoneCode()).isPresent()) {
            throw new RuntimeException("Zone code already exists");
        }

        zone.setZoneCode(zone.getZoneCode().toUpperCase());
        zone.setZoneType(zone.getZoneType().toUpperCase());

        return repository.save(zone);
    }

    // Get all Zones
    public List<Zone> getAllZones() {
        return repository.findAll();
    }

    // Get Zone by ID
    public Zone getZoneById(Long id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Zone not found"));
    }

    // Get Zone by Code
    public Zone getByZoneCode(String zoneCode) {

        return repository.findByZoneCode(zoneCode.toUpperCase())
                .orElseThrow(() ->
                        new RuntimeException("Zone not found"));
    }

    // Get Zones by Type
    public List<Zone> getByZoneType(String zoneType) {
        return repository.findByZoneType(zoneType.toUpperCase());
    }

    // Get Zones by Name
    public List<Zone> getByZoneName(String zoneName) {
        return repository.findByZoneName(zoneName);
    }

    // Update Zone
    public Zone updateZone(Long id, Zone updatedZone) {

        Zone existing = getZoneById(id);

        if (updatedZone.getZoneName() != null &&
                !updatedZone.getZoneName().trim().isEmpty()) {

            existing.setZoneName(updatedZone.getZoneName());
        }

        if (updatedZone.getZoneType() != null &&
                !updatedZone.getZoneType().trim().isEmpty()) {

            existing.setZoneType(
                    updatedZone.getZoneType().toUpperCase());
        }

        if (updatedZone.getCapacity() != null) {

            if (updatedZone.getCapacity() <= 0) {
                throw new RuntimeException(
                        "Capacity must be greater than 0");
            }

            if (updatedZone.getCapacity()
                    < existing.getCurrentOccupancy()) {

                throw new RuntimeException(
                        "Capacity cannot be less than current occupancy");
            }

            existing.setCapacity(updatedZone.getCapacity());
        }

        if (updatedZone.getCurrentOccupancy() != null) {

            if (updatedZone.getCurrentOccupancy() < 0 ||
                    updatedZone.getCurrentOccupancy()
                            > existing.getCapacity()) {

                throw new RuntimeException(
                        "Invalid current occupancy");
            }

            existing.setCurrentOccupancy(
                    updatedZone.getCurrentOccupancy());
        }

        return repository.save(existing);
    }

    // Update Occupancy
    public Zone updateOccupancy(Long id, Integer occupancy) {

        Zone zone = getZoneById(id);

        if (occupancy == null ||
                occupancy < 0 ||
                occupancy > zone.getCapacity()) {

            throw new RuntimeException(
                    "Occupancy must be between 0 and capacity");
        }

        zone.setCurrentOccupancy(occupancy);

        return repository.save(zone);
    }

    // Delete Zone
    public void deleteZone(Long id) {

        if (!repository.existsById(id)) {
            throw new RuntimeException("Zone not found");
        }

        repository.deleteById(id);
    }
}