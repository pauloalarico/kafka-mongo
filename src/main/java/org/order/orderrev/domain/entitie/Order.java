package org.order.orderrev.domain.entitie;

import lombok.Builder;
import lombok.Getter;
import org.order.orderrev.domain.enums.Status;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.ZonedDateTime;

@Builder
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
}

