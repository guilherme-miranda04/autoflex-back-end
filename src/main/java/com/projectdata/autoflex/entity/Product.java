package com.projectdata.autoflex.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true)
    private String code;

    @Column
    private String name;

    @Column
    private BigDecimal price;

    @OneToMany(mappedBy = "product")
    @JsonManagedReference("product-materials")
    private Set<ProductRawMaterial> rawMaterials = new HashSet<>();

    public Product() {
    }

    public Product(String code, String name, BigDecimal price, Set<ProductRawMaterial> rawMaterials) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.rawMaterials = rawMaterials;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Set<ProductRawMaterial> getRawMaterials() {
        return rawMaterials;
    }

    public void setRawMaterials(Set<ProductRawMaterial> rawMaterials) {
        this.rawMaterials = rawMaterials;
    }
}
