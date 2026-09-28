package com.example.WarehouseSlot.Zone.controller;

import com.example.WarehouseSlot.Zone.entity.Zone;
import com.example.WarehouseSlot.Zone.Service.ZoneService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/zones")
public class ZoneController {

    private final ZoneService service;

    public ZoneController(ZoneService service) {
        this.service = service;
    }

    // Create Zone
    @PostMapping
    public Zone createZone(@RequestBody Zone zone) {
        return service.createZone(zone);
    }

    // Get all Zones
    @GetMapping
    public List<Zone> getAllZones() {
        return service.getAllZones();
    }

    // Get Zone by ID
    @GetMapping("/{id}")
    public Zone getZoneById(@PathVariable Long id) {
        return service.getZoneById(id);
    }

    // Get by Zone Code
    @GetMapping("/code/{zoneCode}")
    public Zone getByZoneCode(@PathVariable String zoneCode) {
        return service.getByZoneCode(zoneCode);
    }

    // Get by Zone Type
    @GetMapping("/type/{zoneType}")
    public List<Zone> getByZoneType(
            @PathVariable String zoneType) {

        return service.getByZoneType(zoneType);
    }

    // Get by Zone Name
    @GetMapping("/name/{zoneName}")
    public List<Zone> getByZoneName(
            @PathVariable String zoneName) {

        return service.getByZoneName(zoneName);
    }

    // Update Zone
    @PutMapping("/{id}")
    public Zone updateZone(
            @PathVariable Long id,
            @RequestBody Zone zone) {

        return service.updateZone(id, zone);
    }

    // Update Occupancy
    @PutMapping("/{id}/occupancy")
    public Zone updateOccupancy(
            @PathVariable Long id,
            @RequestParam Integer occupancy) {

        return service.updateOccupancy(id, occupancy);
    }

    // Delete Zone
    @DeleteMapping("/{id}")
    public String deleteZone(@PathVariable Long id) {

        service.deleteZone(id);

        return "Zone deleted successfully";
    }
}