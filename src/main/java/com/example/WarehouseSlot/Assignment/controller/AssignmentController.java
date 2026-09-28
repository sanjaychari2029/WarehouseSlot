package com.example.WarehouseSlot.Assignment.controller;

import com.example.WarehouseSlot.Assignment.entity.Assignment;
import com.example.WarehouseSlot.Assignment.service.AssignmentService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assignments")
public class AssignmentController {

    private final AssignmentService assignmentService;

    public AssignmentController(AssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }


    // Create assignment
    @PostMapping
    public ResponseEntity<Assignment> createAssignment(
            @RequestBody Assignment assignment) {

        Assignment savedAssignment =
                assignmentService.createAssignment(assignment);

        return new ResponseEntity<>(
                savedAssignment,
                HttpStatus.CREATED
        );
    }


    // Get all assignments
    @GetMapping
    public ResponseEntity<List<Assignment>> getAllAssignments() {

        return ResponseEntity.ok(
                assignmentService.getAllAssignments()
        );
    }


    // Get assignment by ID
    @GetMapping("/{id}")
    public ResponseEntity<Assignment> getAssignmentById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                assignmentService.getAssignmentById(id)
        );
    }


    // Get assignments by velocity
    @GetMapping("/velocity/{velocity}")
    public ResponseEntity<List<Assignment>> getByVelocity(
            @PathVariable String velocity) {

        return ResponseEntity.ok(
                assignmentService.getByVelocity(velocity)
        );
    }


    // Get assignments by zone
    @GetMapping("/zone/{zone}")
    public ResponseEntity<List<Assignment>> getByZone(
            @PathVariable String zone) {

        return ResponseEntity.ok(
                assignmentService.getByZone(zone)
        );
    }


    // Delete assignment
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAssignment(
            @PathVariable Long id) {

        assignmentService.deleteAssignment(id);

        return ResponseEntity.ok(
                "Assignment deleted successfully"
        );
    }
}