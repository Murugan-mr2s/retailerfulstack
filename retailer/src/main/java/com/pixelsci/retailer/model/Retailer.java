package com.pixelsci.retailer.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "retailer")
public class Retailer {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "owner_name", nullable = false)
    private String ownerName;
    @Column(name = "address" , nullable = false)
    private String address;
    @Column(name= "email" , nullable = false,unique = true)
    private String email;
    @Column(name="phone",nullable = false,unique = true)
    private String phone;

    @OneToMany(mappedBy = "retailer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Shop> shops;

    public Retailer() {
    }

    public Retailer(String ownerName,
                    String address,
                    String email,
                    String phone,
                    List<Shop> shops) {
        this.ownerName = ownerName;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.shops = shops;
    }

    public Retailer(String email, String phone) {
        this.email = email;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public List<Shop> getShops() {
        return shops;
    }

    public void setShops(List<Shop> shops) {
        this.shops = shops.stream().toList();
    }


}
