package com.geekbrains.service;

import com.geekbrains.entities.OrderItem;
import com.geekbrains.entities.Product;
import com.geekbrains.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {
    private List<OrderItem> items;
    private BigDecimal price;
    private String address;
    private double phone;

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getPhoneNumber() {
        return phone;
    }

    public void setPhoneNumber(double phone) {
        this.phone = phone;
    }

    @PostConstruct
    public void init(){
        items = new ArrayList<>();
    }

    public void add(Product product){
        for (OrderItem i : items){
            if (i.getProduct().getId().equals(product.getId())){
                i.increment();
                recalculate();
                return;
            }
        } items.add(new OrderItem(product));
          recalculate();
    }

        private void recalculate () {
            price = new BigDecimal(0.0);
            items.stream().forEach(orderItem ->
                    price = price.add(orderItem.getPrice()));
        }

}
