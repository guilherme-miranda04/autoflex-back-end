package com.projectdata.autoflex.repository;

import com.projectdata.autoflex.entity.ProductRawMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRawMaterialRepository extends JpaRepository<ProductRawMaterial, UUID> {
    Optional<ProductRawMaterial> findByProductIdAndRawMaterialId(UUID productID, UUID rawMaterialID);
}
