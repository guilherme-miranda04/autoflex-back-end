package com.projectdata.autoflex.dto;

import com.projectdata.autoflex.entity.ProductRawMaterial;

import java.math.BigDecimal;
import java.util.Set;

public record CreateProductDTO(String code, String name, BigDecimal price, Set<ProductRawMaterial> rawMaterials) {
}
