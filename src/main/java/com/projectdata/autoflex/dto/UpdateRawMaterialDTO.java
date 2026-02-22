package com.projectdata.autoflex.dto;

import java.math.BigDecimal;

public record UpdateRawMaterialDTO(String code, String name, BigDecimal stockQuantity) {
}
