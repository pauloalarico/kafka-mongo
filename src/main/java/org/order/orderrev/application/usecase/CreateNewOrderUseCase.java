package org.order.orderrev.application.usecase;

import lombok.RequiredArgsConstructor;
import org.order.orderrev.application.dto.request.NewOrderRequestDTO;
import org.order.orderrev.application.dto.response.NewOrderResponseDTO;
import org.order.orderrev.domain.entitie.Order;
import org.order.orderrev.domain.enums.Status;
import org.order.orderrev.domain.repository.OrderRepository;
import org.order.orderrev.infra.kafka.producer.OrderProducer;
import org.order.orderrev.infra.mapper.OrderMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreateNewOrderUseCase {
    private final OrderProducer orderProducer;
    private final OrderRepository repository;

    @Transactional
    public NewOrderResponseDTO create(NewOrderRequestDTO dto) {
        var order = Order.builder()
                .id(UUID.randomUUID().toString())
                .correlationId(UUID.randomUUID().toString())
                .product(dto.product())
                .quantity(dto.quantity())
                .amount(dto.amount())
                .status(Status.CREATED)
                .createdAt(Instant.now())
                .build();

        repository.save(order);
        var orderDto = OrderMapper.toResponseDto(order);
        orderProducer.sendOrder(orderDto);
        return orderDto;
    }
}
