package com.pixelsci.retailer.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "retailers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Retailer {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "owner_name", nullable = false)
    private String owerName;
    @Column(name = "address" , nullable = false)
    private String address;
    @Column(name= "email" , nullable = false)
    private String email;
    @Column(name="phone",nullable = false)
    private String phone;

    @OneToMany(mappedBy = "retailer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Shop> shops;


    public Retailer(String owerName,
                    String address,
                    String email,
                    String phone,
                    List<Shop> shops) {
        this.owerName = owerName;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.shops = shops;
    }
}
