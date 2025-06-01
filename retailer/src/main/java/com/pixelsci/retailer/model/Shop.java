package com.pixelsci.retailer.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "shops")
@NoArgsConstructor
@AllArgsConstructor
@Data
public  class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name="shop_name",nullable = false)
    private String name;
    @Column(name = "shop_address",nullable = false)
    private String address;

    @ManyToOne
    @JoinColumn(name = "retailer_id")
    private Retailer retailer;

    public Shop(String name, String address) {
        this.name = name;
        this.address = address;
    }
}