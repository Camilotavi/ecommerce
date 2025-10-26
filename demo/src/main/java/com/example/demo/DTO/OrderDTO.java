package com.example.demo.DTO;

import com.example.demo.entity.Order;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderDTO {

    private int quantity;
    private double total;
    private Order.PaymentMethod payment_method;
    private Order.OrderStatus status;
}
