package org.order.orderrev.infra.persistance;

import org.order.orderrev.domain.entitie.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface OrderMongoRepository extends MongoRepository<Order,String> {
    Optional<Order> findByCorrelationId(String correlationId);
}
