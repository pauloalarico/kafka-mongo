package org.order.orderrev.infra.mapper;

import org.order.orderrev.application.dto.response.NewOrderResponseDTO;
import org.order.orderrev.domain.entity.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    public NewOrderResponseDTO toResponseDto(Order order) {
        return new NewOrderResponseDTO(order);
    }
}
