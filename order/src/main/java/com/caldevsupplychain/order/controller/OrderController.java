package com.caldevsupplychain.order.controller;

import com.caldevsupplychain.order.vo.OrderWS;
import org.springframework.http.ResponseEntity;

public interface OrderController {
	ResponseEntity<?> createOrder(OrderWS orderWS);
}
