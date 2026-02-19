package org.order.orderrev.infra.kafka.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.order.orderrev.application.dto.request.OrderProcessDTO;
import org.order.orderrev.application.usecase.ActivateOrderUseCase;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderProcessConsumer {

    private final ActivateOrderUseCase useCase;

    @KafkaListener(topics = "${apps.topic-consumer}")
    public void execute(ConsumerRecord<String, OrderProcessDTO> consumerRecord, Acknowledgment acknowledgment) {
        try {
            useCase.verify(consumerRecord.value());
            acknowledgment.acknowledge();
            log.info("Order job finished with success!");
        } catch (Exception e) {
            log.error("Unable to verify the order processed, topic: {}, error: {}", consumerRecord.topic(), e.getMessage());
        }
    }
}
