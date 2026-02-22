package com.projectdata.autoflex.dto;

import java.math.BigDecimal;

public record UpdateProductDTO(String code, String name, BigDecimal price) {
}
