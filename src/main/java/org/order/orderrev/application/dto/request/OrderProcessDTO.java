package org.order.orderrev.application.dto.request;

import java.math.BigDecimal;

public record OrderProcessDTO(
        String status,
        String correlationId,
        BigDecimal totalValue
) {
}
