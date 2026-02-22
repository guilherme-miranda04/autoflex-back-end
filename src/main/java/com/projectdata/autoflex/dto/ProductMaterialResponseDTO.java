package com.projectdata.autoflex.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductMaterialResponseDTO(UUID materialID, String materialName, String materialCode,
                                         BigDecimal quantityNeeded) {
}
