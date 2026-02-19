package org.order.orderrev.application.usecase;

import lombok.RequiredArgsConstructor;
import org.order.orderrev.application.dto.request.ExternalOrderStatus;
import org.order.orderrev.application.dto.request.OrderProcessDTO;
import org.order.orderrev.domain.repository.OrderRepository;
import org.order.orderrev.domain.exception.OrderNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ActivateOrderUseCase {
    private final OrderRepository repository;

    public void verify(OrderProcessDTO dto) {
        var order = repository.findByCorrelationId(dto.correlationId()).orElseThrow(() -> new OrderNotFoundException("document not found for correlation id " + dto.correlationId()));

        ExternalOrderStatus status;
        try{
            status = ExternalOrderStatus.valueOf(dto.status());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid external status: " + dto.status());
        }

        if(status == ExternalOrderStatus.ACTIVE) {
            order.finish(dto.totalValue());
            repository.save(order);
        }

        else if (status == ExternalOrderStatus.CANCELED) {
            order.cancel();
            repository.save(order);
        }
    }
}
