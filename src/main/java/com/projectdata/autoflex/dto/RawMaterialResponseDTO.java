package com.projectdata.autoflex.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record RawMaterialResponseDTO(UUID id,
                                     String code,
                                     String name,
                                     BigDecimal stockQuantity,
                                     List<RawMaterialProductResponseDTO> usedInProducts) {
}
