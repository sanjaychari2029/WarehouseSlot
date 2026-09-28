package com.example.WarehouseSlot.PackList.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "pack_lists")
public class PackList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String packListNumber;

    @Column(nullable = false)
    private String orderNumber;

    @Column(nullable = false)
    private String itemName;

    @Column(nullable = false)
    private Integer quantity;

    private String status;

    public PackList() {
    }

    public PackList(String packListNumber, String orderNumber,
                    String itemName, Integer quantity, String status) {
        this.packListNumber = packListNumber;
        this.orderNumber = orderNumber;
        this.itemName = itemName;
        this.quantity = quantity;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getPackListNumber() {
        return packListNumber;
    }

    public void setPackListNumber(String packListNumber) {
        this.packListNumber = packListNumber;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}