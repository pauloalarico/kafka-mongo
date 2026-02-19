package org.order.orderrev.application.dto.response;

import org.order.orderrev.domain.entity.Order;
import org.order.orderrev.domain.enums.Status;

import java.math.BigDecimal;
import java.time.Instant;

public record NewOrderResponseDTO(
        String orderId,
        String correlationId,
        Status status,
        String product,
        Integer quantity,
        BigDecimal price,
        Instant createdAt
){
    public NewOrderResponseDTO(Order order) {
        this(order.getId(), order.getCorrelationId(), order.getStatus(), order.getProduct(),
                order.getQuantity(), order.getAmount(), order.getCreatedAt());
    }
}
