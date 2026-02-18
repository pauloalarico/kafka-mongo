package org.order.orderrev.infra.mapper;

import org.order.orderrev.application.dto.response.NewOrderResponseDTO;
import org.order.orderrev.domain.entitie.Order;

public class OrderMapper {

    public static NewOrderResponseDTO toResponseDto(Order order) {
        return new NewOrderResponseDTO(order);
    }
}
