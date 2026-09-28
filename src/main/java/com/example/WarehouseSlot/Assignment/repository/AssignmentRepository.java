package com.example.WarehouseSlot.Assignment.repository;

import com.example.WarehouseSlot.Assignment.entity.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Long> {

    List<Assignment> findByVelocity(String velocity);

    List<Assignment> findByAssignedZone(String assignedZone);
}