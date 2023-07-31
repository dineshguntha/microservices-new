package com.dguntha.orderservice.controller;

import com.dguntha.orderservice.dto.OrderRequest;
import com.dguntha.orderservice.model.Order;
import com.dguntha.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public  String placeOrder(@RequestBody OrderRequest orderRequest) {
        orderService.placeOrder(orderRequest);
        return "successfully order placed";
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Order> findAll() {
        return orderService.findAllOrders();
    }
}
