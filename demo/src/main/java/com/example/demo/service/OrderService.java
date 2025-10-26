package com.example.demo.service;


import com.example.demo.DTO.OrderDTO;
import com.example.demo.entity.Order;
import com.example.demo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    public Order addOrder(OrderDTO order, int user_id){

        Order orden = new Order();

        if (simularPago(String.valueOf(order.getPayment_method()))){
            orden.setUserId(user_id);
            orden.setQuantity(order.getQuantity());
            orden.setTotal(order.getTotal());
            orden.setPaymentMethod(order.getPayment_method());
            orden.setStatus(Order.OrderStatus.completed);
        }else{
            orden.setStatus(Order.OrderStatus.failed);
        }

        return this.repository.save(orden);
    }

    public boolean simularPago(String pago){
        if (pago=="card" || pago=="code"){
            //tambien se puede simular tiempo
            return true;
        }else{
            return false;
        }
    }

    public Optional<Order> getOrderById(Integer id) {
        return repository.findById(id);
    }

    public java.util.List<Order> getOrdersByUserId(Integer userId) {
        return repository.findByUserId(userId);
    }
}
