package org.order.orderrev.application.dto.request;

public record OrderProcessDTO(
        String status,
        String correlationId
) {
}
