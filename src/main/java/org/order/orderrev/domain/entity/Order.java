package org.order.orderrev.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.order.orderrev.domain.enums.Status;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Document(collection = "order")
public class Order {
    @Id
    private String id;
    private String correlationId;
    private String product;
    private Integer quantity;
    private BigDecimal amount;
    private Status status;
    private Instant createdAt;
    private BigDecimal totalValue;

    public static Order create(String product, BigDecimal amount, Integer quantity) {
        var order = new Order();
        order.id = UUID.randomUUID().toString();
        order.correlationId = UUID.randomUUID().toString();
        order.product = product;
        order.quantity = quantity;
        order.amount = amount;
        order.status = Status.CREATED;
        order.createdAt = Instant.now();
        return order;
    }

    public void finish(BigDecimal totalValue) {
        this.status = Status.FINISHED;
        this.totalValue = totalValue;
    }

    public void cancel() {
        this.status = Status.CANCELED;
    }
}

