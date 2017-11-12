package com.caldevsupplychain.order.controller;

import org.springframework.http.ResponseEntity;

import com.caldevsupplychain.order.vo.OrderWS;

public interface OrderController {

	ResponseEntity<?> createOrder(OrderWS orderWS);

	ResponseEntity<?> updateOrder(String orderUuid, OrderWS orderWS);

	ResponseEntity<?> deleteOrder(String orderUuid);

	ResponseEntity<?> readOrder(String orderUuid);

	ResponseEntity<?> readOrdersByUserUuid(String userUuid);

	ResponseEntity<?> readOrdersByAgentUuid(String agentUuid);

}
