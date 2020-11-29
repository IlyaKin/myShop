package com.geekbrains.frontend;

import com.geekbrains.entities.OrderItem;
import com.geekbrains.service.CartService;
import com.geekbrains.service.OrderService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.validator.RegexpValidator;
import com.vaadin.flow.router.Route;
import org.springframework.format.annotation.NumberFormat;

import java.math.BigDecimal;

@Route("cart")
public class CartView  extends AbstractView{
    private  final CartService cartService;
    private final OrderService orderService;

    public CartView(CartService cartService, OrderService orderService) {
        this.cartService = cartService;
        this.orderService = orderService;
        initCartPage();
    }

    private void initCartPage() {
        Grid<OrderItem> grid = new Grid<>(OrderItem.class);
        grid.setItems(cartService.getItems());
        grid.setWidth("50%");
        grid.setColumns("product", "price", "quantity");

        grid.addColumn(new ComponentRenderer<>(item -> {
            Button plusButton = new Button("+", i -> {
                item.increment();
                grid.setItems(cartService.getItems());
            });
            Button minusButton = new Button("-", i -> {
                item.decrement();
                if(item.getQuantity() < 0) {
                    item.setQuantity(0);
                    return;
                }
                grid.setItems(cartService.getItems());
            });

            return new HorizontalLayout(plusButton, minusButton);
        }));
        TextField addressField = initTextFieldWithPlaceholder("Введите адрес доставки");
        addressField.setWidth("15%");
        NumberField phoneField = initNumberFieldWithPlaceholder("Введите номер телефона");
        phoneField.setWidth("15%");
        Button toOrderButton = new Button("Создать заказ", e->{
            cartService.setAddress(addressField.getValue());
            cartService.setPhone(String.valueOf(phoneField.getValue()));
            orderService.saveOrder();

            Notification.show("Заказ успешно сохранён и передан менеджеру");
        });
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        setHorizontalComponentAlignment(Alignment.CENTER, grid);
        add(grid,addressField,phoneField,toOrderButton);
    }
}
