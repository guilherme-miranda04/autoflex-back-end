package com.projectdata.autoflex.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "product_raw_material")
public class ProductRawMaterial {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    @JsonIgnoreProperties("product-materials")
    private Product product;

    @ManyToOne(optional = false)
    @JoinColumn(name = "raw_material_id", nullable = false)
    @JsonIgnoreProperties("material-products")
    private RawMaterial rawMaterial;

    @Column(nullable = false)
    private BigDecimal quantityNeeded;

    public ProductRawMaterial() {
    }

    public ProductRawMaterial(UUID id, Product product, RawMaterial rawMaterial, BigDecimal quantityNeeded) {
        this.id = id;
        this.product = product;
        this.rawMaterial = rawMaterial;
        this.quantityNeeded = quantityNeeded;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public RawMaterial getRawMaterial() {
        return rawMaterial;
    }

    public void setRawMaterial(RawMaterial rawMaterial) {
        this.rawMaterial = rawMaterial;
    }

    public BigDecimal getQuantityNeeded() {
        return quantityNeeded;
    }

    public void setQuantityNeeded(BigDecimal quantityNeeded) {
        this.quantityNeeded = quantityNeeded;
    }
}
