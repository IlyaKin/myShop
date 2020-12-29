package com.geekbrains.repository;

import com.geekbrains.entities.OrderItem;
import org.springframework.data.repository.CrudRepository;

public interface OrderItemRepository extends CrudRepository<OrderItem, Long> {
    void deleteByProductTitle(String title);
}
