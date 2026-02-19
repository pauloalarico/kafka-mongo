package org.order.orderrev.application.usecase;

import lombok.RequiredArgsConstructor;
import org.order.orderrev.application.dto.request.NewOrderRequestDTO;
import org.order.orderrev.application.dto.response.NewOrderResponseDTO;
import org.order.orderrev.domain.entity.Order;
import org.order.orderrev.domain.repository.OrderRepository;
import org.order.orderrev.infra.kafka.producer.OrderProducer;
import org.order.orderrev.infra.mapper.OrderMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateNewOrderUseCase {
    private final OrderProducer orderProducer;
    private final OrderRepository repository;
    private final OrderMapper mapper;

    public NewOrderResponseDTO create(NewOrderRequestDTO dto) {
        var order = Order.create(dto.product(), dto.amount(), dto.quantity());

        repository.save(order);
        var orderDto = mapper.toResponseDto(order);
        orderProducer.sendOrder(orderDto);
        return orderDto;
    }
}
