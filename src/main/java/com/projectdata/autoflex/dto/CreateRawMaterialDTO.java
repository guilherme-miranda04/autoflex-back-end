package com.projectdata.autoflex.dto;

import com.projectdata.autoflex.entity.ProductRawMaterial;

import java.math.BigDecimal;
import java.util.Set;

public record CreateRawMaterialDTO(String code, String name, BigDecimal stockQuantity,
                                   Set<ProductRawMaterial> products) {
}
