package com.projectdata.autoflex.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "raw_material")
public class RawMaterial {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true)
    private String code;

    @Column
    private String name;

    @Column
    private BigDecimal stockQuantity;

    @OneToMany(mappedBy = "rawMaterial")
    @JsonManagedReference("material-products")
    private Set<ProductRawMaterial> products = new HashSet<>();

    public RawMaterial() {
    }

    public RawMaterial(String code, String name, BigDecimal stockQuantity, Set<ProductRawMaterial> products) {
        this.code = code;
        this.name = name;
        this.stockQuantity = stockQuantity;
        this.products = products;
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

    public BigDecimal getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(BigDecimal stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public Set<ProductRawMaterial> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductRawMaterial> products) {
        this.products = products;
    }
}
