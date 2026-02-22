package com.projectdata.autoflex.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record AddRawMaterialDTO(UUID materialID, BigDecimal quantityNeeded) {
}
