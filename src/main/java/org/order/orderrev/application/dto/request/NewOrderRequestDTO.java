package org.order.orderrev.application.dto.request;

import java.math.BigDecimal;

public record NewOrderRequestDTO(
        String product,
        Integer quantity,
        BigDecimal amount
) {
}
