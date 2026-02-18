package org.order.orderrev.infra.kafka.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.order.orderrev.application.dto.response.NewOrderResponseDTO;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderProducer {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendOrder(NewOrderResponseDTO dto) {
        kafkaTemplate.send("order-topic", dto.orderId(), dto)
                .whenComplete((success, e) -> {
                    if (e != null) {
                        log.warn("Could not send the order, {}", e.getMessage());
                    }
                    else {
                        log.info("Order sent: id {}, correlation id: {}", dto.orderId(), dto.correlationId());
                    }
        });

    }
}
