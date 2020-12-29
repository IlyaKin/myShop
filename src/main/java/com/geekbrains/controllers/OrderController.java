package com.geekbrains.controllers;

import com.geekbrains.aspect.Log;
import com.geekbrains.entities.Order;
import com.geekbrains.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("shop/v1/order")
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;

    @Log
    @GetMapping
    public List<Order> findAllOrders(){
        return orderRepository.findAll();
    }

}
