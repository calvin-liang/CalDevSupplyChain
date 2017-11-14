package com.caldevsupplychain.order.controller;

import com.caldevsupplychain.account.annotation.RequiresJwtAuthentication;
import com.caldevsupplychain.account.service.AccountService;
import com.caldevsupplychain.common.exception.ApiErrorsExceptionHandler;
import com.caldevsupplychain.common.type.ErrorCode;
import com.caldevsupplychain.common.ws.ApiErrorsWS;
import com.caldevsupplychain.common.ws.ErrorWS;
import com.caldevsupplychain.order.service.OrderService;
import com.caldevsupplychain.order.util.OrderMapper;
import com.caldevsupplychain.order.validator.OrderValidator;
import com.caldevsupplychain.order.vo.OrderWS;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@Api(value="/api/v1/orders", description = "Order API")
@RequestMapping("/api/v1/orders")
public class OrderControllerImpl implements OrderController {

	private OrderService orderService;
	private AccountService accountService;

	private OrderValidator orderValidator;

	private OrderMapper orderMapper;

	private ApiErrorsExceptionHandler apiErrorsExceptionHandler;


	@PostMapping("/order")
	@RequiresJwtAuthentication
	@RequiresPermissions("order:create")
	public ResponseEntity<?> createOrder(@Validated @RequestBody OrderWS orderWS) {

		// check user_id
		if(!accountService.findById(orderWS.getUserId()).isPresent()){
			log.error("Error in create order. User id={} not found", orderWS.getUserId().toString());
			return new ResponseEntity<>(new ApiErrorsWS(ErrorCode.USER_ID_NOT_FOUND.name(), "In create order user id={}" + orderWS.getUserId().toString().toString() + " not found"), HttpStatus.NOT_FOUND);
		}

		// check agent_id
		if(!accountService.findById(orderWS.getAgentId()).isPresent()){
			log.error("Error in create order. Agent id={} not found", orderWS.getAgentId().toString());
			return new ResponseEntity<>(new ApiErrorsWS(ErrorCode.AGENT_ID_NOT_FOUND.name(), "In create order agent id={}" + orderWS.getAgentId().toString().toString() + " not found"), HttpStatus.NOT_FOUND);
		}

		BindException errors = new BindException(orderWS, "OrderWS");

		orderValidator.validate(orderWS, errors);

		if (errors.hasErrors()) {
			log.error("Error in create order. Fail in order validation fields. orderWS={}", orderWS.toString());
			List<ErrorWS> errorWSList = apiErrorsExceptionHandler.generateErrorWSList(errors);
			return new ResponseEntity<>(new ApiErrorsWS(errorWSList), HttpStatus.UNPROCESSABLE_ENTITY);
		}

//		OrderBean orderBean = orderMapper.toBean(orderWS);
//		OrderBean order = orderService.createOrder(orderBean);


		return null;
	}



}
