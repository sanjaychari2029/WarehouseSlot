package com.example.WarehouseSlot.Assignment.service;

import com.example.WarehouseSlot.Assignment.entity.Assignment;
import com.example.WarehouseSlot.Assignment.repository.AssignmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssignmentService {

    private final AssignmentRepository assignmentRepository;

    public AssignmentService(AssignmentRepository assignmentRepository) {
        this.assignmentRepository = assignmentRepository;
    }

    // Create assignment
    public Assignment createAssignment(Assignment assignment) {

        // Basic validation
        if (assignment.getItemName() == null ||
                assignment.getItemName().trim().isEmpty()) {

            throw new RuntimeException("Item name is required");
        }

        if (assignment.getQuantity() == null ||
                assignment.getQuantity() <= 0) {

            throw new RuntimeException("Quantity must be greater than 0");
        }

        if (assignment.getVelocity() == null ||
                assignment.getVelocity().trim().isEmpty()) {

            throw new RuntimeException("Velocity is required");
        }

        // Convert velocity to uppercase
        String velocity = assignment.getVelocity().toUpperCase();

        // Business rule
        if (velocity.equals("FAST")) {

            assignment.setPreferredZone("NEAR_DISPATCH");

        } else if (velocity.equals("SLOW")) {

            assignment.setPreferredZone("FAR");

        } else {

            throw new RuntimeException(
                    "Velocity must be FAST or SLOW"
            );
        }

        /*
         * Initially assign the preferred zone.
         * Later, when Bin entity is created,
         * this logic can select an actual available bin.
         */
        assignment.setAssignedZone(
                assignment.getPreferredZone()
        );

        assignment.setVelocity(velocity);

        return assignmentRepository.save(assignment);
    }


    // Get all assignments
    public List<Assignment> getAllAssignments() {

        return assignmentRepository.findAll();
    }


    // Get assignment by ID
    public Assignment getAssignmentById(Long id) {

        return assignmentRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Assignment not found with id: " + id
                        )
                );
    }


    // Get assignments by velocity
    public List<Assignment> getByVelocity(String velocity) {

        return assignmentRepository.findByVelocity(
                velocity.toUpperCase()
        );
    }


    // Get assignments by zone
    public List<Assignment> getByZone(String zone) {

        return assignmentRepository.findByAssignedZone(
                zone.toUpperCase()
        );
    }


    // Delete assignment
    public void deleteAssignment(Long id) {

        if (!assignmentRepository.existsById(id)) {

            throw new RuntimeException(
                    "Assignment not found with id: " + id
            );
        }

        assignmentRepository.deleteById(id);
    }
}