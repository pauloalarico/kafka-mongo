package org.order.orderrev.infra.kafka.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.order.orderrev.application.dto.response.NewOrderResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderProducer {
    private final KafkaTemplate<String, Object> kafkaTemplate;
    @Value("${apps.order-create-topic}")
    private String orderTopic;

    public void sendOrder(NewOrderResponseDTO dto) {
        try {
            kafkaTemplate.send(orderTopic, dto.orderId(), dto).get();
        } catch (ExecutionException e) {
            log.error("Unable to send message, error: {}", e.getMessage());
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.error("Thread interrupt while sending message, error: {}", e.getMessage());
            throw new RuntimeException("Interrupted Exception: " + e.getMessage());
        }

    }
}
