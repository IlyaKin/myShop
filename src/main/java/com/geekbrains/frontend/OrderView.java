package com.geekbrains.frontend;

import com.geekbrains.entities.Order;
import com.geekbrains.service.OrderService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.router.Route;

@Route("order")
public class OrderView extends AbstractView {
    private final OrderService orderService;

    public OrderView(OrderService orderService) {
        this.orderService = orderService;
        initOrderView();
    }

    private void initOrderView() {
        Grid<Order> orderGrid = new Grid<>(Order.class);
        setWidth("50%");
        orderGrid.setItems(orderService.getByUserId(1L));

        add(orderGrid);
    }

}
