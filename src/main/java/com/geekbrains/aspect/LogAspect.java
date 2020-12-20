package com.geekbrains.aspect;

import com.geekbrains.entities.Order;
import com.geekbrains.repository.OrderRepository;
import com.geekbrains.service.OrderService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogManager;
import org.apache.log4j.Logger;

@Aspect
@Component
public class LogAspect {

    private static final Logger logger = Logger.getLogger(LogAspect.class);
    @Autowired
    private OrderService orderService;


    @Around("@annotation(log)")
    protected Object object(ProceedingJoinPoint p, Log log) throws Throwable {
        System.out.println("Попали в логирование");
        long timeBefore = System.currentTimeMillis();
        p.proceed();
        long timeAfter = System.currentTimeMillis();
        long executionTime = timeAfter - timeBefore;
        System.out.printf("[Время выполения метода]: %d мс.", executionTime);
        List<Order> order = orderService.getByUserId(1L);
        String name = order.get(0).getUser().getPhone();
        String address = order.get(0).getAddress();
        double number = order.get(0).getPhoneNumber();
        long phoneNumber = (long) number;
        String price = String.valueOf(order.get(0).getPrice());
        String status = String.valueOf(order.get(0).getStatus());
        String items = String.valueOf(order.get(0).getItems());
        String logging = "Name = " + " " + name+";" + " " + "Address = " + " " + address+";" + " " + "Phone = " + phoneNumber+";" + " " +
                "Price = "+  price+";" + " " + "Status = " + status+";" + " " + "Products = " + items;
        logger.info(String.valueOf(logging));
        return p;
    }
    }

