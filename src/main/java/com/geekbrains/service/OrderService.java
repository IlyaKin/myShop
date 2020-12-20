package com.geekbrains.service;

import com.geekbrains.aspect.Log;
import com.geekbrains.entities.Order;
import com.geekbrains.entities.OrderItem;
import com.geekbrains.entities.User;
import com.geekbrains.repository.OrderItemRepository;
import com.geekbrains.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.geekbrains.entities.Order.Status.MANAGING;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartService cartService;
    private final UserService userService;
    private final OrderItemRepository orderItemRepository;

    public OrderService(OrderRepository orderRepository,
                        CartService cartService,
                        UserService userService,
                        OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.cartService = cartService;
        this.userService = userService;
        this.orderItemRepository = orderItemRepository;
    }
    @Log
    public void saveOrder() {
        User user = userService.findById(1L);

        Order order = new Order();
        order.setItems(cartService.getItems());
        order.setAddress(cartService.getAddress());
        order.setPhoneNumber(cartService.getPhoneNumber());
        order.setUser(user);
        order.setPrice(cartService.getPrice());
        order.setStatus(MANAGING);

        final Order savedOrder = orderRepository.save(order);
    }

    @Transactional
    public List<Order> getByUserId(long userId) {
        return orderRepository.findAllByUserId(userId);
    }

}