package org.order.orderrev.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record NewOrderRequestDTO(
        @NotBlank
        String product,
        @NotNull
        @Positive
        Integer quantity,
        @Positive
        @NotNull
        BigDecimal amount
) {
}
