package com.projectdata.autoflex.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductionSuggestionDTO(UUID productId, String productName, BigDecimal unitPrice,
                                      BigDecimal quantityPossible, BigDecimal totalValue) {

}
