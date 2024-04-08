package com.ravi.orderservice.controller;

import com.ravi.orderservice.dto.OrderRequest;
import com.ravi.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/placeOrder")
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOder(@RequestBody OrderRequest orderRequest){
        orderService.placeOrder(orderRequest);
        return "Order placed Suuccessfully";
    }


}
