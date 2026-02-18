package org.order.orderrev.application.usecase;

import lombok.RequiredArgsConstructor;
import org.order.orderrev.application.dto.request.OrderProcessDTO;
import org.order.orderrev.domain.repository.OrderRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ActivateOrderUseCase {
    private final OrderRepository repository;

    public void verify(OrderProcessDTO dto) {
        var order = repository.findByCorrelationId(dto.correlationId()).orElseThrow(() -> new RuntimeException("document not found"));
        if(dto.status().equals("ACTIVE")) {
            order.finish();
            repository.save(order);
        }
        if (dto.status().equals("CANCELED")) {
            order.cancel();
        }
    }
}
