package com.projectdata.autoflex.service;

import com.projectdata.autoflex.dto.AddRawMaterialDTO;
import com.projectdata.autoflex.dto.UpdateRawMaterialQuantityDTO;
import com.projectdata.autoflex.entity.ProductRawMaterial;
import com.projectdata.autoflex.repository.ProductRawMaterialRepository;
import com.projectdata.autoflex.repository.ProductRepository;
import com.projectdata.autoflex.repository.RawMaterialRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProductRawMaterialService {

    private ProductRepository productRepository;
    private RawMaterialRepository rawMaterialRepository;
    private ProductRawMaterialRepository productRawMaterialRepository;

    public ProductRawMaterialService(ProductRepository productRepository, RawMaterialRepository rawMaterialRepository, ProductRawMaterialRepository productRawMaterialRepository) {
        this.productRepository = productRepository;
        this.rawMaterialRepository = rawMaterialRepository;
        this.productRawMaterialRepository = productRawMaterialRepository;
    }

    public void addRawMaterialToProduct(String productID, AddRawMaterialDTO addRawMaterialDTO) {
        var prodUUID = UUID.fromString(productID);
        var rawUUID = addRawMaterialDTO.materialID();

        var product = productRepository.findById(prodUUID);

        if (product.isEmpty()) {
            new RuntimeException("Product not found");
        }

        var rawMaterial = rawMaterialRepository.findById(rawUUID);

        if (rawMaterial.isEmpty()) {
            new RuntimeException("Raw material not found");
        }

        var existing = productRawMaterialRepository.findByProductIdAndRawMaterialId(prodUUID, rawUUID);

        if (existing.isPresent()) {
            throw new RuntimeException("Association already exists");
        }

        ProductRawMaterial association = new ProductRawMaterial();
        association.setProduct(product.get());
        System.out.println("product:" + product.get());
        association.setRawMaterial(rawMaterial.get());
        System.out.println("rawMaterial:" + rawMaterial.get());
        association.setQuantityNeeded(addRawMaterialDTO.quantityNeeded());

        productRawMaterialRepository.save(association);
    }

    public void updateRawMaterialQuantity(String productID, String rawMaterialID, UpdateRawMaterialQuantityDTO updateRawMaterialQuantityDTO) {
        var prodUUID = UUID.fromString(productID);
        var rawUUID = UUID.fromString(rawMaterialID);

        var associationOptional = productRawMaterialRepository.findByProductIdAndRawMaterialId(prodUUID, rawUUID);

        if (associationOptional.isEmpty()) {
            throw new RuntimeException("Association not found");
        }

        ProductRawMaterial association = associationOptional.get();

        association.setQuantityNeeded(updateRawMaterialQuantityDTO.quantityNeeded());

        productRawMaterialRepository.save(association);
    }

    public void deleteRawMaterialFromProduct(String productID, String rawMaterialID) {
        var prodUUID = UUID.fromString(productID);
        var rawUUID = UUID.fromString(rawMaterialID);

        var associationOptional = productRawMaterialRepository.findByProductIdAndRawMaterialId(prodUUID, rawUUID);

        if (associationOptional.isEmpty()) {
            throw new RuntimeException("Association not found");
        }

        ProductRawMaterial association = associationOptional.get();

        productRawMaterialRepository.delete(association);
    }
}
