package org.order.orderrev.domain.repository;

import org.order.orderrev.domain.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {
    Order save(Order order);
    Optional<Order> findByOrderId(String id);
    Optional<Order> findByCorrelationId(String id);
    List<Order> findAll();
}
