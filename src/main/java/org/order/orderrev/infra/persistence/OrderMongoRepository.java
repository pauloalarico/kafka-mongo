package org.order.orderrev.infra.persistence;

import org.order.orderrev.domain.entity.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface OrderMongoRepository extends MongoRepository<Order,String> {
    Optional<Order> findByCorrelationId(String correlationId);
}
