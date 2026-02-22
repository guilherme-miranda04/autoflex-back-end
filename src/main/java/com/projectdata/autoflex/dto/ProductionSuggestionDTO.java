package com.projectdata.autoflex.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductionSuggestionDTO(UUID productID, String productName, BigDecimal unitPrice,
                                      BigDecimal quantityPossible, BigDecimal totalValue) {

}
