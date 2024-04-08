package com.ravi.orderservice.repository;

import com.ravi.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository  extends JpaRepository <Order,Long> {
}
