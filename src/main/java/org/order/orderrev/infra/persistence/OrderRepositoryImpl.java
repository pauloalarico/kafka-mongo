package org.order.orderrev.infra.persistence;

import lombok.RequiredArgsConstructor;
import org.order.orderrev.domain.entity.Order;
import org.order.orderrev.domain.repository.OrderRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class OrderRepositoryImpl implements OrderRepository {
    private final OrderMongoRepository orderMongoRepository;

    @Override
    public Order save(Order order) {
        return orderMongoRepository.save(order);
    }

    @Override
    public Optional<Order> findByOrderId(String id) {
        return orderMongoRepository.findById(id);
    }

    @Override
    public Optional<Order> findByCorrelationId(String id) {
        return orderMongoRepository.findByCorrelationId(id);
    }

    @Override
    public List<Order> findAll() {
        return orderMongoRepository.findAll();
    }
}
